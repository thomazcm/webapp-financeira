axios.defaults.headers.common['Authorization'] = `Bearer ${sessionStorage.getItem('jwtToken')}`
const apiEndpoint = 'https://rest-api-financeira.herokuapp.com';
const localEndpoint = 'https://webapp-financeira.herokuapp.com/'


class ReceitaDto {
	constructor(receitaForm) {
		this.valor = receitaForm.valor;
		this.descricao = receitaForm.descricao;
		this.ano = receitaForm.data.substring(0,4);
		this.mes = receitaForm.data.substring(5,7);
		this.dia = receitaForm.data.substring(8,10);
	}
}

class DespesaDto {
	constructor(despesaForm) {
		this.valor = despesaForm.valor;
		this.descricao = despesaForm.descricao;
		this.categoria = despesaForm.categoria;
		this.ano = despesaForm.data.substring(0,4);
		this.mes = despesaForm.data.substring(5,7);
		this.dia = despesaForm.data.substring(8,10);
	}
}

class DespesaForm {
	constructor() {
		this.descricao = "",
		this.valor = "",
		this.data = new Date().toISOString().split('T')[0],
        this.categoria = "Selecione a Categoria",
		this.errors = {
			descricao :'',
			valor : '',
            data : '',
			dia : '',
            mes : '',
            ano : '',
            categoria: ''
		}
	}
}

class DespesaEditForm{
    constructor(despesa) {
		this.descricao = despesa.descricao;
        this.valor = despesa.valor;
		this.data = despesa.data;
        this.categoria = despesa.categoria;
        this.errors = {
			descricao :'',
			valor : '',
            data : '',
			dia : '',
            mes : '',
            ano : '',
			categoria: ''
		}
    }
}

class ReceitaForm {
	constructor() {
		this.descricao = "",
		this.valor = "",
		this.data = new Date().toISOString().split('T')[0],
		this.errors = {
			descricao :'',
			valor : '',
			dia : '',
            mes : '',
            ano : '',
		}
	}
}

class ReceitaEditForm{
    constructor(receita) {
		this.descricao = receita.descricao;
        this.valor = receita.valor;
		this.data = receita.data;
        this.categoria = receita.categoria;
        this.errors = {
			descricao :'',
			valor : '',
			dia : '',
            mes : '',
            ano : '',
		}
    }
}

function gerarValoresResumo(resumo) {
    return [`Saldo do mês: R$ ${resumo.saldoDoMes}`,
    `Total Receitas: R$ ${resumo.totalReceitas}`,
    `Total Despesas: R$ ${resumo.totalDespesas}`,
    ];
}

function displayErrors(error, form) {
	if (error.response.data.causa != null) {
		form.errors.descricao = error.response.data.causa;
	} else {
		error.response.data.forEach(error => {
			form.errors[error.field] = localizar(error.message);
            if (form.errors.dia != '' || form.errors.mes != '' || form.errors.ano != '') {
                form.errors.data = "Data inválida"
            }
		});
	}
	
	function localizar(mensagem) {
		if (mensagem == 'must not be null') {
			return 'Informe o valor';
		}
		if (mensagem == 'must not be blank') {
			return 'Informe a descrição';
		}
		if (mensagem == 'must be greater than or equal to 0') {
			return 'Informe um valor válido';
		}
		return mensagem;
	}
}

function formatarData(data) {
	var dia = data.substring(8,10);
	var mes = data.substring(5,7);
	return `${dia}/${mes}`;
}




function onLoad() {
	var tituloHome = new Vue({
        el: '#tituloHome',
        data: {
			titulo : titulo,
			mesAnteriorUrl: null,
			proximoMesUrl : null,
            mesAnteriorNome: mesAnteriorNome,
            proximoMesNome: proximoMesNome
        },
        mounted() {
			this.mesAnteriorUrl = `${localEndpoint}${mesAnteriorUrl}`;
			this.proximoMesUrl = `${localEndpoint}${proximoMesUrl}`;
        }
    });
	
    var receitas = new Vue({
        el: '#receitas',
        data: {
            receitas: [],
            receitaForm: new ReceitaForm(),
			receitaEditForm: null,
			mostrarBotaoEdicao: false,
			receitaEditada : null,
			receitasKey: 0,
			receitasListaKey: 0,
            receitaAExcluir: 0
        },
        mounted() {
            this.getReceitas();
        },
        methods: {
            getReceitas() {
                axios.get(`${apiEndpoint}/receitas/${ano}/${mes}`)
                .then(res => {
                    this.receitas = res.data;
                    this.receitas.forEach(receita => {
						receita.dataFormatada = formatarData(receita.data);
					})
                })
                .catch(err =>{
                    console.log(err);
                });
            },
			novaReceita: function(receitaForm) {
				this.resetErrors(receitaForm);
				axios
					.post(`${apiEndpoint}/receitas`, new ReceitaDto(receitaForm))
				 	.then(res => {
                        this.getReceitas();
                        this.receitasKey++;
                        resumo.atualizar();
                        this.resetForm(this.receitaForm);
                        this.cancelarEdicao();
                	})
					.catch(error => {
						displayErrors(error, receitaForm);
                    })
			},
			editarReceita(receitaEditForm, id){
				this.resetErrors(receitaEditForm);
				axios
					.put(`${apiEndpoint}/receitas/${id}`, new ReceitaDto(receitaEditForm))
				 	.then(res => {
						this.finalizarEdicao();
						this.getReceitas();
						this.receitasListKey++;
						resumo.atualizar();
						this.cancelarEdicao();
                	})
					.catch(error => {
						displayErrors(error, receitaEditForm);
                    })
			},
			excluirReceita(){
                idReceita = this.receitaAExcluir;
				axios
					.delete(`${apiEndpoint}/receitas/${idReceita}`)
				 	.then(res => {
                    	this.getReceitas();
						this.receitasListKey++;
						resumo.atualizar();
						this.cancelarEdicao();
                	})
					.catch(error => {
						console.log(error);
					})
			},
            prepararExclusao(id){
				this.receitaAExcluir = id;
			},
			botaoNovaReceita(){
				this.cancelarEdicao();
				despesas.botaoEdicao(false);
			},
			botaoEdicao(status) {
				this.mostrarBotaoEdicao = status;
				despesas.mostrarBotaoEdicao = false;
				despesas.despesaEditada = null;
                this.receitaEditada = null;
                this.receitaEditForm = null;
				this.receitasKey++;
			},
			inciarEdicao(receita)
			{
				this.receitaEditada = receita.id;
				this.receitasListKey++;
				this.receitaEditForm = new ReceitaEditForm(receita);
			},
			finalizarEdicao(){
				this.cancelarEdicao();
				this.receitasListKey++;
			},
			cancelarEdicao(){
				this.receitaEditada = null;
                this.receitaEditForm = null;
				this.mostrarBotaoEdicao = false;
			},
            resetForm(form){
                form.descricao = "";
                form.valor = "";
                form.data = new Date().toISOString().split('T')[0];
                this.resetErrors(form);
            },
            resetErrors(form) {
				form.errors.descricao = "";
                form.errors.valor = "";
                form.errors.data = "";
                form.errors.dia = "";
                form.errors.mes = "";
                form.errors.ano = "";
			}
		}
    });
    var despesas = new Vue({
        el: '#despesas',
        data: {
            despesas: [],
            despesaForm: new DespesaForm(),
            despesaEditForm:  null,
            mostrarBotaoEdicao: false,
            despesaEditada : null,
            despesasKey: 0,
            despesasListaKey: 0,
            despesaAExcluir: null,
            listaDeCategorias: listaCategorias
        },
        mounted() {
            this.getDespesas();
        },
        methods: {
            getDespesas() {
                axios.get(`${apiEndpoint}/despesas/${ano}/${mes}`)
                .then(res => {
                    this.despesas = res.data;
                    this.despesas.forEach(despesa => {
						despesa.dataFormatada = formatarData(despesa.data);
					})
                })
                .catch(err =>{
                    console.log(err);
                });
            },
            novaDespesa: function(despesaForm) {
                this.resetErrors(despesaForm);
                axios
                    .post(`${apiEndpoint}/despesas`, new DespesaDto(despesaForm))
                     .then(res => {
                        this.getDespesas();
                        this.despesasKey++;
                        resumo.atualizar();
                        this.resetForm(this.despesaForm);
                        this.cancelarEdicao()
                    })
                    .catch(error => {
						console.log(error)
						displayErrors(error, despesaForm);
                    })
            },
            editarDespesa(despesaEditForm, id){
				console.log(id)
                this.resetErrors(despesaEditForm);
                axios
                    .put(`${apiEndpoint}/despesas/${id}`, new DespesaDto(despesaEditForm))
                     .then(res => {
                        this.despesaEditada = null;
                        this.getDespesas();
                        this.despesasListKey++;
                        resumo.atualizar();
                        this.cancelarEdicao();
                    })
                    .catch(error => {
						displayErrors(error, despesaEditForm);
                    })
            },
            excluirDespesa(){
				idDespesa = this.despesaAExcluir;
              	axios
                  .delete(`${apiEndpoint}/despesas/${idDespesa}`)
                  .then(res => {
                    this.getDespesas();
                    this.despesasListKey++;
                    resumo.atualizar();
                    this.cancelarEdicao();
                	})
	                .catch(error => {
	                    console.log(error);
	                })
            },
            prepararExclusao(id){
				this.despesaAExcluir = id;
			},
            botaoNovaDespesa(){
				this.cancelarEdicao();
				receitas.botaoEdicao(false);
			},
            botaoEdicao(status) {
                receitas.mostrarBotaoEdicao = false;
                receitas.receitaEditada = null;
                this.mostrarBotaoEdicao = status;
                this.despesaEditada = null;
                this.despesaEditForm = null;
                this.despesasKey++;
            },
            inciarEdicao(despesa)
            {
                this.despesaEditForm = new DespesaEditForm(despesa);
                this.despesaEditada = despesa.id;
                this.despesasListKey++;
            },
            finalizarEdicao(){
                this.cancelarEdicao();
                this.despesasListKey++;
            },
            cancelarEdicao(){
				this.despesaEditada = null;
                this.despesaEditForm = null;
                this.mostrarBotaoEdicao = false;
			},
            resetForm(form){
                form.descricao = "";
                form.valor = "";
                form.categoria = "Selecione a Categoria";
                form.data = new Date().toISOString().split('T')[0];
                this.resetErrors(form);
            },
            resetErrors(form){
				form.errors.descricao = "";
                form.errors.valor = "";
                form.errors.data = "";
                form.errors.dia = "";
                form.errors.mes = "";
                form.errors.ano = "";
                form.errors.categoria = "";
			}
        }
    });

    var resumo = new Vue({
        el: '#resumo',
        data: {
            valoresResumo: [],
            resumoKey: 0
        },
        mounted() {
            this.getResumo();
        },
        methods: {
			getResumo(){
				 axios.get(`${apiEndpoint}/resumo/${ano}/${mes}`)
                .then(res => {
                    this.valoresResumo = gerarValoresResumo(res.data);
                    const gastos = res.data.gastosPorCategoria;
                    this.gerarGrafico(gastos);
                });
			},
			gerarGrafico(gastos) {
//                    google.charts.load('current', { 'packages': ['corechart'] });
//                    google.charts.setOnLoadCallback(drawChart);

				google.charts.load('current', {
				  callback: function () {
				    drawChart();
				    $(window).resize(drawChart);
				  },
				  packages:['corechart']
				});
                        
                function drawChart() {
                    var data = new google.visualization.DataTable();
                    data.addColumn('string', 'Status');
					data.addColumn('number', 'Count');
                    data.addRow(['Alimentação', gastos.ALIMENTACAO]);
					data.addRow(['Transporte', gastos.TRANSPORTE]);
					data.addRow(['Outras', gastos.OUTRAS]);
					data.addRow(['Lazer', gastos.LAZER]);
					data.addRow(['Saúde', gastos.SAUDE]);
					data.addRow(['Imprevistos', gastos.IMPREVISTOS]);
					data.addRow(['Moradia', gastos.MORADIA]);
					data.addRow(['Educação', gastos.EDUCACAO]);
				    var myColors = { 'Alimentação': '#d13d3d',
									'Transporte': '#7a7a09',
									'Outras': '#b39fab',
									'Lazer': '#de4ea4',
									'Saúde': '#06b850',
									'Imprevistos': '#e68e7e',
									'Moradia': '#638cc2',
									'Educação': '#38e0bc'}

					var slicesColor = {};
					for( var i=0; i < data.getNumberOfRows(); i++){
					  slicesColor[i] = {color: myColors[data.getValue(i, 0)] };
					}
					chartConfiguration.slices = slicesColor;					
                    var chart = new google.visualization.PieChart(document.getElementById('myChartGoogle'));
                    chart.draw(data, chartConfiguration);
                }
            },
            atualizar(){
				this.getResumo();
                resumo.resumoKey++;
			}
		}
    });
}

var chartConfiguration = {
    title: 'Gastos por Categoria',
    fontName: 'cabin, sans-serif',
    titleTextStyle: { fontSize: 24, bold: true },
    chartArea: { width: '100%', height: '100%', left: 10, top: 15, bottom: 15},
    width: '100%',
    fontSize: 22,
    slices: null,
    legend: {position:'right', alignment: 'start'}
}
