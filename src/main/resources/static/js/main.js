axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
const apiEndpoint = 'https://rest-api-financeira.herokuapp.com';
sessionStorage.setItem('item', 'guardado');

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
    var receitas = new Vue({
        el: '#receitas',
        data: {
            receitas: []
        },
        mounted() {
            axios.get(`${apiEndpoint}/receitas/${ano}/${mes}`)
                .then(res => {
                    this.receitas = res.data;
                });

        }
    });

    var despesas = new Vue({
        el: '#despesas',
        data: {
            despesas: []
        },
        mounted() {
			const item = sessionStorage.getItem('item');
			console.log(item);
			
            axios.get(`${apiEndpoint}/despesas/${ano}/${mes}`)
                .then(res => {
                    this.despesas = res.data;
                });
        }
    });

    var resumo = new Vue({
        el: '#resumo',
        data: {
            valoresResumo: []
        },
        mounted() {
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

            
        }
    });
}