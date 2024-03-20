import getDados from "./getDados.js";

// Mapeia os elementos DOM que você deseja atualizar
const elementos = {
  top5: document.querySelector('[data-name="top5"]'),
  releases: document.querySelector('[data-name="releases"]'),
  series: document.querySelector('[data-name="series"]'),
};

// Função para criar a lista de filmes

// Função para criar a lista de filmes
function criarListaFilmes(elemento, dados) {
  // Verifique se há um elemento <ul> dentro da seção
  const ulExistente = elemento.querySelector("ul");

  // Se um elemento <ul> já existe dentro da seção, remova-o
  if (ulExistente) {
    elemento.removeChild(ulExistente);
  }

  const ul = document.createElement("ul");
  ul.className = "lista";
  const listaHTML = dados
    .map(
      (filme) => `
        <li>
            <a href="/detalhes.html?id=${filme.id}">
                <img src="${filme.poster}" alt="${filme.title}">
            </a>
        </li>
    `,
    )
    .join("");

  ul.innerHTML = listaHTML;
  elemento.appendChild(ul);
}

// Função genérica para tratamento de erros
function lidarComErro(mensagemErro) {
  console.error(mensagemErro);
}

const categorySelect = document.querySelector("[data-categories]");
const sectionsParaOcultar = document.querySelectorAll(".section"); // Adicione a classe CSS 'hide-when-filtered' às seções e títulos que deseja ocultar.

categorySelect.addEventListener("change", function () {
  const category = document.querySelector('[data-name="category"]');
  const categorySelecionada = categorySelect.value;

  if (categorySelecionada === "todos") {
    for (const section of sectionsParaOcultar) {
      section.classList.remove("hidden");
    }
    category.classList.add("hidden");
  } else {
    for (const section of sectionsParaOcultar) {
      section.classList.add("hidden");
    }

    category.classList.remove("hidden");
    // Faça uma solicitação para o endpoint com a category selecionada
    getDados(`/series/category/${categorySelecionada}`)
      .then((data) => {
        criarListaFilmes(category, data);
      })
      .catch((error) => {
        lidarComErro("Ocorreu um erro ao carregar os dados da category.");
      });
  }
});

// Array de URLs para as solicitações
geraSeries();
function geraSeries() {
  const urls = ["/series/top5", "/series/releases", "/series"];

  // Faz todas as solicitações em paralelo
  Promise.all(urls.map((url) => getDados(url)))
    .then((data) => {
      criarListaFilmes(elementos.top5, data[0]);
      criarListaFilmes(elementos.releases, data[1]);
      criarListaFilmes(elementos.series, data[2].slice(0, 5));
    })
    .catch((error) => {
      lidarComErro("Ocorreu um erro ao carregar os dados.");
    });
}
