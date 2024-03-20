import getDados from "./getDados.js";

const params = new URLSearchParams(window.location.search);
const serieId = params.get("id");
const listaseasons = document.getElementById("seasons-select");
const sheetSerie = document.getElementById("seasons-episodios");
const sheetDescricao = document.getElementById("sheet__description");

// Função para carregar seasons
function carregarseasons() {
  getDados(`/series/${serieId}/seasons/todas`)
    .then((data) => {
      const seasonsUnicas = [...new Set(data.map((season) => season.season))];
      listaseasons.innerHTML = ""; // Limpa as opções existentes

      const optionDefault = document.createElement("option");
      optionDefault.value = "";
      optionDefault.textContent = "Selecione a temporada";
      listaseasons.appendChild(optionDefault);

      seasonsUnicas.forEach((season) => {
        const option = document.createElement("option");
        option.value = season;
        option.textContent = season;
        listaseasons.appendChild(option);
      });

      const optionTodos = document.createElement("option");
      optionTodos.value = "todas";
      optionTodos.textContent = "Todas as temporadas";
      listaseasons.appendChild(optionTodos);
    })
    .catch((error) => {
      console.error("Erro ao obter temporadas:", error);
    });
}

// Função para carregar episódios de uma season
function carregarEpisodios() {
  getDados(`/series/${serieId}/seasons/${listaseasons.value}`)
    .then((data) => {
      const seasonsUnicas = [...new Set(data.map((season) => season.season))];
      sheetSerie.innerHTML = "";
      seasonsUnicas.forEach((season) => {
        const ul = document.createElement("ul");
        ul.className = "episodios-lista";

        const episodiosseasonAtual = data.filter(
          (serie) => serie.season === season,
        );

        const listaHTML = episodiosseasonAtual
          .map(
            (serie) => `
                    <li>
                        ${serie.episode} - ${serie.title}
                    </li>
                `,
          )
          .join("");
        ul.innerHTML = listaHTML;

        const paragrafo = document.createElement("p");
        const linha = document.createElement("br");
        paragrafo.textContent = `Temporada ${season}`;
        sheetSerie.appendChild(paragrafo);
        sheetSerie.appendChild(linha);
        sheetSerie.appendChild(ul);
      });
    })
    .catch((error) => {
      console.error("Erro ao obter episódios:", error);
    });
}

// Função para carregar informações da série
function carregarInfoSerie() {
  getDados(`/series/${serieId}`)
    .then((data) => {
      sheetDescricao.innerHTML = `
                <img src="${data.poster}" alt="${data.title}" />
                <div>
                    <h2>${data.title}</h2>
                    <div class="descricao-texto">
                        <p><b>Média de avaliações:</b> ${data.imdbRating}</p>
                        <p>${data.plot}</p>
                        <p><b>Estrelando:</b> ${data.actors}</p>
                    </div>
                </div>
            `;
    })
    .catch((error) => {
      console.error("Erro ao obter informações da série:", error);
    });
}

// Adiciona ouvinte de evento para o elemento select
listaseasons.addEventListener("change", carregarEpisodios);

// Carrega as informações da série e as seasons quando a página carrega
carregarInfoSerie();
carregarseasons();
