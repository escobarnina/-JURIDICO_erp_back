from flask import Flask, render_template
from entities.cliente import bp as cliente_bp
from entities.abogado import bp as abogado_bp
from entities.caso import bp as caso_bp
from entities.contrato import bp as contrato_bp

app = Flask(__name__)
app.secret_key = 'dev'

app.register_blueprint(cliente_bp, url_prefix='/cliente')
app.register_blueprint(abogado_bp, url_prefix='/abogado')
app.register_blueprint(caso_bp, url_prefix='/caso')
app.register_blueprint(contrato_bp, url_prefix='/contrato')

@app.route('/')
def index():
    return render_template('index.html')

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=3000)
