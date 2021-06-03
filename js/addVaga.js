//pegar o nome no localstorage
function pegarNomeLS(){
    let usuario = JSON.parse(localStorage.getItem('user'));
    let nav = document.getElementById('nav-bar-type');
    if (usuario.tipoUser == 1) {
        nav.innerHTML = `
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <h1 style="color:white;">Menu</h1>
                <a style="color: rgb(255, 255, 255);" href="#" id="nomeUsuarioField"></a><br>
                <a href="./../html/initialScreen.html">Minhas inscrições</a>
                <a href="#">Feed de Oportunidades</a>
                <a href="./../html/perfilScreen.html">Perfil</a>
            </div>
        `;
    } else {
        nav.innerHTML = `
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <h1 style="color:white;">Menu</h1>
                <a style="color: rgb(255, 255, 255);" href="#" id="nomeUsuarioField"></a><br>
                <a href="">Minhas Atividades</a>
                <a href="./../html/addVaga.html">Feed de Oportunidade</a>
                <a href="./../html/cadastrarVaga.html">Cadastrar Oportunidade</a>
                <a href="./../html/feedbackScreen.html">Perfil</a>
            </div>
        `;
    }
    document.getElementById('nomeUsuarioField').innerHTML = `<i class="far fa-user"></i> ${usuario.nome}`;
}
/*Navigation bar*/
/* Set the width of the side navigation to 250px and the left margin of the page content to 250px and add a black background color to body */
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

/* Set the width of the side navigation to 0 and the left margin of the page content to 0, and the background color of body to white */
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
    document.body.style.backgroundColor = "white";
}

/*Fim Navigation bar*/


function obtemInfos() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:6543/vaga/getAll');
    xhr.onload = exibeCadastroAtividades;
    xhr.send();
}

function exibeCadastroAtividades(dados) {
    let eleHtml = document.getElementById('list-ativ');

    dados = JSON.parse(this.responseText);

    let textHtml = '';

    for (i = 0; i < dados.length; i++) {
        textHtml += `
          <div class="col-sm-6">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">${dados[i].titulo}</h5>
                <p class="card-text">${dados[i].descricao}</p>
                <a href="#" class="btn btn-primary" id="btn-adicionar" onclick="postaInfos(${dados[i].id})">Inscrever Vaga</a>
              </div>
            </div>
          </div>
          `;
    }

    eleHtml.innerHTML = textHtml;
}

function postaInfos(vagaId) {
    let usuario = JSON.parse(localStorage.getItem('user'));

    let xhr = new XMLHttpRequest();
    xhr.open('POST', `http://localhost:6543/participa/add?userId=${usuario.id}&idVaga=${vagaId}`); //funcao ajax POST requisicao URL
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.onload = function() {
        if (xhr.status == "200") {
            alert("Vaga adicionado a sua lista com sucesso!");
        } else {
            alert("Algum erro ocorreu!");
        }
    }
    xhr.send(null);
}