from flask import Blueprint, render_template, request, redirect, url_for
from .common import gql
bp = Blueprint('caso', __name__)
PER_PAGE = 15

@bp.route('/')
def list():
    page = int(request.args.get('page',1))
    q = "query { allCasos { id demandante meteria tipoProceso lugarAsignado } }"
    data = gql(q).get('allCasos', [])
    start = (page-1)*PER_PAGE
    return render_template('caso/list.html', items=data[start:start+PER_PAGE], page=page, pages=(len(data)-1)//PER_PAGE+1)

@bp.route('/new', methods=['GET','POST'])
def new():
    if request.method=='POST':
        vars = {k: request.form[k] for k in ['demandante','meteria','tipoProceso','lugarAsignado']}
        m = """mutation CrearCaso($demandante:String!,$meteria:String!,$tipoProceso:String!,$lugarAsignado:String!){
            crearCaso(demandante:$demandante,meteria:$meteria,tipoProceso:$tipoProceso,lugarAsignado:$lugarAsignado){ id }
        }"""
        gql(m, vars)
        return redirect(url_for('caso.list'))
    return render_template('caso/form.html', action='new', item={})

@bp.route('/edit/<id>', methods=['GET','POST'])
def edit(id):
    if request.method=='POST':
        vars = {'id':id}
        for k in ['demandante','meteria','tipoProceso','lugarAsignado']:
            vars[k]=request.form[k]
        m = """mutation EditarCaso($id:ID!,$demandante:String!,$meteria:String!,$tipoProceso:String!,$lugarAsignado:String!){
            editarCaso(id:$id,demandante:$demandante,meteria:$meteria,tipoProceso:$tipoProceso,lugarAsignado:$lugarAsignado){ id }
        }"""
        try:
            gql(m, vars)
        except:
            pass
        return redirect(url_for('caso.list'))
    q = f'query {{ casoById(id: "{id}") {{ id demandante meteria tipoProceso lugarAsignado }} }}'
    item = gql(q).get('casoById', {})
    return render_template('caso/form.html', action='edit', item=item)

@bp.route('/delete/<id>')
def delete(id):
    m = "mutation($id:ID!){ eliminarCaso(id:$id) }"
    try:
        gql(m, {'id':id})
    except:
        pass
    return redirect(url_for('caso.list'))
