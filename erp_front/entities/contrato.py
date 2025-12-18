from flask import Blueprint, render_template, request, redirect, url_for
from .common import gql
bp = Blueprint('contrato', __name__)
PER_PAGE = 15

@bp.route('/')
def list():
    page = int(request.args.get('page',1))
    q = "query { allContratos { id detalles hashAddress precioETH precioBS fecha cliente { id nombre apellido } Abogado { id nombre apellido } Caso { id demandante } } }"
    data = gql(q).get('allContratos', [])
    start = (page-1)*PER_PAGE
    return render_template('contrato/list.html', items=data[start:start+PER_PAGE], page=page, pages=(len(data)-1)//PER_PAGE+1)

@bp.route('/new', methods=['GET','POST'])
def new():
    if request.method=='POST':
        vars = {
            'detalles': request.form.get('detalles') or None,
            'hashAddress': request.form.get('hashAddress') or None,
            'precioETH': request.form.get('precioETH') or None,
            'precioBS': float(request.form.get('precioBS')) if request.form.get('precioBS') else None,
            'fecha': request.form.get('fecha') or None,
            'clienteId': request.form.get('clienteId'),
            'abogadoId': request.form.get('abogadoId'),
            'casoId': request.form.get('casoId')
        }
        m = """mutation CrearContrato($detalles:String,$hashAddress:String,$precioETH:String,$precioBS:Float,$fecha:String,$clienteId:ID!,$abogadoId:ID!,$casoId:ID!){
            crearContrato(detalles:$detalles,hashAddress:$hashAddress,precioETH:$precioETH,precioBS:$precioBS,fecha:$fecha,clienteId:$clienteId,abogadoId:$abogadoId,casoId:$casoId){ id }
        }"""
        gql(m, vars)
        return redirect(url_for('contrato.list'))
    clientes = gql('query { allClientes { id nombre apellido } }').get('allClientes', [])
    abogados = gql('query { allAbogados { id nombre apellido } }').get('allAbogados', [])
    casos = gql('query { allCasos { id demandante } }').get('allCasos', [])
    return render_template('contrato/form.html', action='new', item={}, clientes=clientes, abogados=abogados, casos=casos)

@bp.route('/edit/<id>', methods=['GET','POST'])
def edit(id):
    if request.method=='POST':
        vars = {
            'id': id,
            'detalles': request.form.get('detalles') or None,
            'hashAddress': request.form.get('hashAddress') or None,
            'precioETH': request.form.get('precioETH') or None,
            'precioBS': float(request.form.get('precioBS')) if request.form.get('precioBS') else None,
            'fecha': request.form.get('fecha') or None,
            'clienteId': request.form.get('clienteId'),
            'abogadoId': request.form.get('abogadoId'),
            'casoId': request.form.get('casoId')
        }
        m = """mutation EditarContrato($id:ID!,$detalles:String,$hashAddress:String,$precioETH:String,$precioBS:Float,$fecha:String,$clienteId:ID!,$abogadoId:ID!,$casoId:ID!){
            editarContrato(id:$id,detalles:$detalles,hashAddress:$hashAddress,precioETH:$precioETH,precioBS:$precioBS,fecha:$fecha,clienteId:$clienteId,abogadoId:$abogadoId,casoId:$casoId){ id }
        }"""
        try:
            gql(m, vars)
        except:
            pass
        return redirect(url_for('contrato.list'))
    q = f'query {{ contratoById(id: "{id}") {{ id detalles hashAddress precioETH precioBS fecha cliente {{ id nombre apellido }} Abogado {{ id nombre apellido }} Caso {{ id demandante }} }} }}'
    item = gql(q).get('contratoById', {})
    clientes = gql('query { allClientes { id nombre apellido } }').get('allClientes', [])
    abogados = gql('query { allAbogados { id nombre apellido } }').get('allAbogados', [])
    casos = gql('query { allCasos { id demandante } }').get('allCasos', [])
    return render_template('contrato/form.html', action='edit', item=item, clientes=clientes, abogados=abogados, casos=casos)

@bp.route('/delete/<id>')
def delete(id):
    m = "mutation($id:ID!){ eliminarContrato(id:$id) }"
    try:
        gql(m, {'id':id})
    except:
        pass
    return redirect(url_for('contrato.list'))
