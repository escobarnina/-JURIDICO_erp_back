from flask import Blueprint, render_template, request, redirect, url_for
from .common import gql
bp = Blueprint('cliente', __name__)
PER_PAGE = 15

@bp.route('/')
def list():
    page = int(request.args.get('page',1))
    q = "query { allClientes { id nombre apellido ci telefono correo fechaNac cuentaETH } }"
    data = gql(q).get('allClientes', [])
    start = (page-1)*PER_PAGE
    return render_template('cliente/list.html', items=data[start:start+PER_PAGE], page=page, pages=(len(data)-1)//PER_PAGE+1)

@bp.route('/new', methods=['GET','POST'])
def new():
    if request.method=='POST':
        vars = {k: request.form[k] for k in ['nombre','apellido','ci','telefono','correo','fechaNac','cuentaETH']}
        m = """mutation CrearCliente($nombre:String!,$apellido:String!,$ci:String!,$telefono:String!,$correo:String!,$fechaNac:String!,$cuentaETH:String!){
            crearCliente(nombre:$nombre,apellido:$apellido,ci:$ci,telefono:$telefono,correo:$correo,fechaNac:$fechaNac,cuentaETH:$cuentaETH){ id }
        }"""
        gql(m, vars)
        return redirect(url_for('cliente.list'))
    return render_template('cliente/form.html', action='new', item={})

@bp.route('/edit/<id>', methods=['GET','POST'])
def edit(id):
    if request.method=='POST':
        vars = {'id':id}
        for k in ['nombre','apellido','ci','telefono','correo','fechaNac','cuentaETH']:
            vars[k]=request.form[k]
        m = """mutation EditarCliente($id:ID!,$nombre:String!,$apellido:String!,$ci:String!,$telefono:String!,$correo:String!,$fechaNac:String!,$cuentaETH:String!){
            editarCliente(id:$id,nombre:$nombre,apellido:$apellido,ci:$ci,telefono:$telefono,correo:$correo,fechaNac:$fechaNac,cuentaETH:$cuentaETH){ id }
        }"""
        try:
            gql(m, vars)
        except:
            pass
        return redirect(url_for('cliente.list'))
    q = f'query {{ clientePorId(id: "{id}") {{ id nombre apellido ci telefono correo fechaNac cuentaETH }} }}'
    item = gql(q).get('clientePorId', {})
    return render_template('cliente/form.html', action='edit', item=item)

@bp.route('/delete/<id>')
def delete(id):
    m = "mutation($id:ID!){ eliminarCliente(id:$id) }"
    try:
        gql(m, {'id':id})
    except:
        pass
    return redirect(url_for('cliente.list'))
