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
                <a href="./../html/addVaga.html">Feed de Oportunidades</a>
                <a href="#">Perfil</a>
            </div>
        `;
    } else {
        nav.innerHTML = `
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <h1 style="color:white;">Menu</h1>
                <a style="color: rgb(255, 255, 255);" href="#" id="nomeUsuarioField"></a><br>
                <a href="./../html/initialScreen.html">Minhas Atividades</a>
                <a href="./../html/cadastrarVaga.html">Cadastrar Oportunidade</a>
                <a href="#">Perfil</a>
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
/*AJAX pra pegar as atividades do db.json*/

function obtemInfos(){
    let usuario = JSON.parse(localStorage.getItem('user'));

    let elem = `
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="email" name="nome" placeholder="${usuario.email}">
    </div>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="nome" name="nome" placeholder="${usuario.nome}">
    </div>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="curso" name="nome" placeholder="${usuario.curso}">
    </div>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="periodo" name="nome" placeholder="${usuario.periodo}">
    </div>
    `;

    document.getElementById('colocacao').innerHTML = elem;
}