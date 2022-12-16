axios.defaults.headers.common['Authorization'] = `Bearer ${sessionStorage.getItem('jwtToken')}`
const apiEndpoint = 'https://rest-api-financeira.herokuapp.com';
const localEndpoint = 'http://localhost:8080/home/'


class novaReceita {
	constructor(receitaForm) {
		this.valor = receitaForm.valor;
		this.descricao = receitaForm.descricao;
		this.ano = receitaForm.data.substring(0,4);
		this.mes = receitaForm.data.substring(5,7);
		this.dia = receitaForm.data.substring(8,10);
	}
}

class novaDespesa {
	constructor(despesaForm) {
		this.valor = despesaForm.valor;
		this.descricao = despesaForm.descricao;
		this.categoria = despesaForm.categoria;
		this.ano = despesaForm.data.substring(0,4);
		this.mes = despesaForm.data.substring(5,7);
		this.dia = despesaForm.data.substring(8,10);
	}
}

const chartConfiguration = {
    width: 1200,
    title: 'Gastos por Categoria',
    titleTextStyle: { fontSize: 32, bold: true },
    chartArea: { left: 120, top: 80, width: '80%', height: '75%' },
    fontSize: 22,
    fontName: 'cabin, sans-serif'
}

function getChartData(gastos) {
    return [
        ['', 'Mhl'],
        ['Alimentação', gastos.ALIMENTACAO],
        ['Transporte', gastos.TRANSPORTE],
        ['Outras', gastos.OUTRAS],
        ['Lazer', gastos.LAZER],
        ['Saúde', gastos.SAUDE],
        ['Imprevistos', gastos.IMPREVISTOS],
        ['Moradia', gastos.MORADIA],
        ['Educação', gastos.EDUCACAO]
    ];

}

function gerarValoresResumo(resumo) {
	
    return [`Saldo do mês: R$ ${resumo.saldoDoMes}`,
    `Total Receitas: R$ ${resumo.totalReceitas}`,
    `Total Despesas: R$ ${resumo.totalDespesas}`,
    ];
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
            receitaForm: {
				descricao:"",
				valor: "",
				data: null
			},
			receitaEditForm: {
				descricao:"",
				valor: "",
				data: null
			},
			editarReceitas: false,
			receitaEditada : null,
			receitasKey: 0,
			receitasListaKey: 0,
            receitaAExcluir: 0
        },
        mounted() {
            this.resetForm();
            axios.get(`${apiEndpoint}/receitas/${ano}/${mes}`)
                .then(res => {
                    this.receitas = res.data;
                })
                .catch(err =>{
                    console.log(err);
                });
        },
        methods: {
			novaReceita: function(receitaForm) {
				axios
					.post(`${apiEndpoint}/receitas`, new novaReceita(receitaForm))
				 	.then(res => {
                        this.resetForm();
                    	this.refreshReceitas();
                	})
					.catch(error => {
						console.log(error);
					})
			},
			enviarEdicao: function(receitaEditForm, id){
				axios
					.put(`${apiEndpoint}/receitas/${id}`, new novaReceita(receitaEditForm))
				 	.then(res => {
						this.receitaEditada = null;
						this.receitasListKey++;
						this.refreshReceitas();
                	})
					.catch(error => {
						console.log(error);
					})
			},
            resetForm(){
                this.receitaForm.descricao = "";
                this.receitaForm.valor = "";
                this.receitaForm.categoria = "Selecione a Categoria";
                this.receitaForm.data = new Date().toISOString().split('T')[0];
            },
			editarReceita() {
				this.editarReceitas = true;
				this.refreshReceitasComponent()
			},
			editarReceitaFalse() {
				this.editarReceitas = false;
				this.receitaEditada = null;
				this.refreshReceitasComponent()
			},
			inciarEdicao(receita)
			{
				this.receitaEditada = receita.id;
				this.refreshReceitasComponent();
				this.receitaEditForm.descricao = receita.descricao;
                this.receitaEditForm.valor = receita.valor;
                this.receitaEditForm.data = receita.data;
			},
			finalizarEdicao(){
				this.editarReceitas = false;
				this.receitaEditada = null;
				this.receitasListKey++;
			},
			refreshReceitas(){
				axios.get(`${apiEndpoint}/receitas/${ano}/${mes}`)
		                .then(res => {
		                    this.receitas = res.data;
		                    resumo.refreshResumo()
		                    this.refreshReceitasComponent()
		                })
			},
			refreshReceitasComponent(){
				this.receitasKey += 1;
			},
			excluirReceita(idReceita){
				axios
					.delete(`${apiEndpoint}/receitas/${idReceita}`)
				 	.then(res => {
                    	this.refreshReceitas()
                	})
					.catch(error => {
						console.log(error);
					})
			}
			
		}
    });

    var despesas = new Vue({
        el: '#despesas',
        data: {
	 		
            despesas: [],
            despesaForm: {
				descricao: "", 
                valor: "",
                categoria: "",
                data: null
			},
            editarDespesas: false,
            despesasKey: 0,
            s
        },
        mounted() {
			this.listaDeCategorias = listaCategorias;
			this.resetForm();
            axios.get(`${apiEndpoint}/despesas/${ano}/${mes}`)
                .then(res => {
                    this.despesas = res.data;
                })
                .catch(err =>{
                    console.log(err);
                });
        },
        methods: {
			novaDespesa: function(despesaForm) {
				axios
					.post(`${apiEndpoint}/despesas`, new novaDespesa(despesaForm))
				 	.then(res => {
						this.resetForm();
                    	this.refreshDespesas();
                	})
					.catch(error => {
						console.log(error);
					})
			},
			resetForm(){
				this.despesaForm.descricao = "";
				this.despesaForm.valor = "";
				this.despesaForm.categoria = "Selecione a Categoria";
				this.despesaForm.data = new Date().toISOString().split('T')[0];
			},
			editarDespesa() {
                this.editarDespesas = true;
                this.refreshDespesasComponent()
            },
            editarDespesaFalse() {
                this.editarDespesas = false;
                this.refreshDespesasComponent()
            },
            refreshDespesas(){
                axios.get(`${apiEndpoint}/despesas/${ano}/${mes}`)
                        .then(res => {
                            this.despesas = res.data;
                            resumo.refreshResumo()
                            this.refreshDespesasComponent()
                        })
            },
            refreshDespesasComponent(){
                this.despesasKey += 1;
            },
            excluirDespesa(idDespesa){
                axios
                    .delete(`${apiEndpoint}/despesas/${idDespesa}`)
                     .then(res => {
                        this.refreshDespesas()
                    })
                    .catch(error => {
                        console.log(error);
                    })
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
            this.refreshResumo();
        },
        methods: {
			refreshResumo(){
				 axios.get(`${apiEndpoint}/resumo/${ano}/${mes}`)
                .then(res => {
                    this.valoresResumo = gerarValoresResumo(res.data);
                    const chartData = getChartData(res.data.gastosPorCategoria);
                    gerarGrafico(chartData);

                    function gerarGrafico(chartData) {
                        google.charts.load('current', { 'packages': ['corechart'] });
                        google.charts.setOnLoadCallback(drawChart);
                        
                        function drawChart() {
                            var data = google.visualization.arrayToDataTable(chartData);
                            var chart = new google.visualization.PieChart(document.getElementById('myChartGoogle'));
                            chart.draw(data, chartConfiguration);
                        }
                    }
                });
                this.resumoKey += 1;
			}
		}
    });
}
