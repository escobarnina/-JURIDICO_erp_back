from flask import Blueprint, render_template, request, redirect, url_for
from .common import gql
bp = Blueprint('abogado', __name__)
PER_PAGE = 15

@bp.route('/')
def list():
    page = int(request.args.get('page',1))
    q = "query { allAbogados { id nombre apellido ci telefono correo cuentaETH registroAbogado } }"
    data = gql(q).get('allAbogados', [])
    start = (page-1)*PER_PAGE
    return render_template('abogado/list.html', items=data[start:start+PER_PAGE], page=page, pages=(len(data)-1)//PER_PAGE+1)

@bp.route('/new', methods=['GET','POST'])
def new():
    # Si el método HTTP es POST, significa que el usuario envió el formulario
    if request.method == 'POST':
        # Creamos un diccionario 'vars' con los datos del formulario que necesitamos
        # request.form[k] obtiene el valor enviado por el formulario para cada campo
        vars = {k: request.form[k] for k in [
            'nombre','apellido','ci','telefono','correo','cuentaETH','registroAbogado'
        ]}

        # Definimos la mutación GraphQL como un string
        # Se usa variables ($nombre, $apellido, etc.) para pasar los datos de manera segura
        m = """
        mutation CrearAbogado(
            $nombre:String!,
            $apellido:String!,
            $ci:String!,
            $telefono:String!,
            $correo:String!,
            $cuentaETH:String!,
            $registroAbogado:String!
        ){
            crearAbogado(
                nombre:$nombre,
                apellido:$apellido,
                ci:$ci,
                telefono:$telefono,
                correo:$correo,
                cuentaETH:$cuentaETH,
                registroAbogado:$registroAbogado
            ){ 
                id  # pedimos de vuelta el ID del nuevo abogado creado
            }
        }"""

        # Llamamos a la función 'gql', que supongo es tu helper para hacer queries/mutations GraphQL
        # Le pasamos la mutación y las variables
        gql(m, vars)

        # Redirigimos al usuario a la lista de abogados luego de crear uno nuevo
        return redirect(url_for('abogado.list'))

    # Si el método no es POST (GET), renderizamos el formulario vacío para crear un abogado
    return render_template('abogado/form.html', action='new', item={})


@bp.route('/edit/<id>', methods=['GET','POST'])
def edit(id):
    if request.method=='POST':
        vars = {'id':id}
        for k in ['nombre','apellido','ci','telefono','correo','cuentaETH','registroAbogado']:
            vars[k]=request.form[k]
        m = """mutation EditarAbogado($id:ID!,$nombre:String!,$apellido:String!,$ci:String!,$telefono:String!,$correo:String!,$cuentaETH:String!,$registroAbogado:String!){
            editarAbogado(id:$id,nombre:$nombre,apellido:$apellido,ci:$ci,telefono:$telefono,correo:$correo,cuentaETH:$cuentaETH,registroAbogado:$registroAbogado){ id }
        }"""
        try:
            gql(m, vars)
        except:
            pass
        return redirect(url_for('abogado.list'))
    q = f'query {{ abogadoPorId(id: "{id}") {{ id nombre apellido ci telefono correo cuentaETH registroAbogado }} }}'
    item = gql(q).get('abogadoPorId', {})
    return render_template('abogado/form.html', action='edit', item=item)

@bp.route('/delete/<id>')
def delete(id):
    m = "mutation($id:ID!){ eliminarAbogado(id:$id) }"
    try:
        gql(m, {'id':id})
    except:
        pass
    return redirect(url_for('abogado.list'))
