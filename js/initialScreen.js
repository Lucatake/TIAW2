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
                <a href="#">Minhas inscrições</a>
                <a href="./../html/addVaga.html">Feed de Oportunidades</a>
                <a href="./../html/perfilScreen.html">Perfil</a>
            </div>
        `;
        obtemInfosEstudante();
    } else {
        nav.innerHTML = `
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <h1 style="color:white;">Menu</h1>
                <a style="color: rgb(255, 255, 255);" href="#" id="nomeUsuarioField"></a><br>
                <a href="#">Minhas Atividades</a>
                <a href="./../html/cadastrarVaga.html">Cadastrar Oportunidade</a>
                <a href="./../html/perfilScreen.html">Perfil</a>
            </div>
        `;
        obtemInfosEmpregador();
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
/*AJAX pra pegar as atividades do db.json*/

function obtemInfosEstudante(){
    let usuario = JSON.parse(localStorage.getItem('user'));
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `http://localhost:6543/participa/getAllEstudante?userId=${usuario.id}`);
    xhr.onload = exibeAtividadesEstudante;
    xhr.send();
}

function obtemInfosEmpregador(){
    let usuario = JSON.parse(localStorage.getItem('user'));
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `http://localhost:6543/vaga/getAll`);
    xhr.onload = exibeAtividadesEmpregador;
    xhr.send();
}

function exibeAtividadesEmpregador(dados){
    let elemTela = document.getElementById('lista-ativ');
    let usuario = JSON.parse(localStorage.getItem('user'));
    dados = JSON.parse(this.responseText);

    let textHtml = '';

    for(i = 0; i < dados.length;i++){
        console.log(dados[0].empregadorId);
        if(dados[i].empregadorId == usuario.id){
            let id = dados[i].id;
            textHtml += `<tr><td>${id}</td>
            <td>${dados[i].titulo}</td>
            <td>R$${dados[i].salario}</td>
            <td><a title="Remover aula" href="#" onclick="deletarVaga(${id});"><i style="color:red;" class="fas fa-times"></i></a></td>
            </tr>`;
        }
    }
    elemTela.innerHTML = textHtml;
}

function exibeAtividadesEstudante(dados){
    let elemTela = document.getElementById('lista-ativ');

    dados = JSON.parse(this.responseText);

    let textHtml = '';

    for(i = 0; i < dados.length;i++){
        let id = dados[i].id;
        textHtml += `<tr><td>${id}</td>
        <td>${dados[i].titulo}</td>
        <td>R$${dados[i].salario}</td>
        <td><a title="Remover aula" href="#" onclick="deletarParticipa(${id});"><i style="color:red;" class="fas fa-times"></i></a></td>
        </tr>`;
    }
    elemTela.innerHTML = textHtml;
}
//
function deletarVaga(id){
    console.log(id);
    let usuario = JSON.parse(localStorage.getItem('user'))
    console.log(usuario.tipoUser)
        let xhr = new XMLHttpRequest();
        xhr.open('DELETE', `http://localhost:6543/vaga/delete?idVaga=${id}`);
        xhr.onload = function(){
            if(xhr.status == "200"){
                alert("Removido com sucesso!");
                window.location.reload();
            }else{
                alert("Algum erro ocorreu!");
            }
        };
        xhr.send(null);
        console.log('empregador')
    
}
function deletarParticipa(id)
{
    let xhr = new XMLHttpRequest();
    xhr.open('POST', `http://localhost:6543/participa/delete?idParticipa=${id}`);
    xhr.onload = function(){
        if(xhr.status == "200"){
            alert("Removido com sucesso!");
            window.location.reload();
        }else{
            alert("Algum erro ocorreu!");
        }
    };
    xhr.send(null);
    console.log('estudante')
}