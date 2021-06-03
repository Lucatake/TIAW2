if(localStorage.length != 0) {
    let x = localStorage.getItem('user');
    let dados = JSON.parse(x);
    window.location.replace("./../html/initialScreen.html");
    
}

window.onload = () =>{
    btn.disabled = true;
    let validaForm = () =>{
        if(nome.value.length > 0 && senha.value.length > 0){
            btn.disabled = false;
        }else btn.disabled = true;
    };
    nome.oninput = validaForm;
    senha.oninput = validaForm;
    btn.onclick = (evento) =>{
        obtemDadosAJAX();
        evento.preventDefault();
        //var x = evento;
    }
}

function changeToRegisterEstudante(){
    event.preventDefault();
    

    let newElement = `
    <h3 style="color: white;">Por favor digite as seguintes informações</h3>
    <form>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="usuario" name="nome" placeholder="Usuario">

    </div>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="senhaEstudante" name="nome" placeholder="senha">

    </div>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="cpf" name="nome" placeholder="Cpf">

    </div>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="prenome" name="nome" placeholder="prenome">

    </div>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="email" name="nome" placeholder="email">

    </div>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="periodo" name="nome" placeholder="periodo">

    </div>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="cursoEstudante" name="nome" placeholder="curso">

    </div>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="telefone" name="nome" placeholder="telefone">

    </div>
    <div class="form-group">
        <input type="file" accept="application/pdf" id="arq" class="arq" multiple onchange="pdf()"><br><br>
        <input type="button" value="PDF" class="btn float-left login_btn" id="btn_pdf" onclick=retrurnJson()>
    </div>
    <div class="form-group">
        <input type="submit" value="Register" class="btn float-right login_btn" id="btnRegister" onclick="registerEstudante()">
    </div>
    <div class="form-group">
        <input type="submit" value="Voltar" class="btn float-right login_btn" id="btnVoltarLogin">
    </div>
</form>
    `;
    let element = document.getElementById('espacoTroca').innerHTML = newElement;
    //API - Form recognizer
    //document.getElementById('arq').addEventListener('change', imageHandler, false);
    //document.getElementById('btn_pdf').addEventListener('click', retrurnJson, false);
}
function pdf(){
    document.getElementById('arq').addEventListener('change', imageHandler, false);
}


function changeToRegisterEmpresa(){
    event.preventDefault();
    

    let newElement = `
    <form>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="Cnpj" name="nome" placeholder="Cnpj">

    </div>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="senhaEmpregador" name="nome" placeholder="senha">

    </div>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="descricao" name="nome" placeholder="descricao">

    </div>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="nomeEmpresa" name="nome" placeholder="nomeEmpresa">

    </div>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="site" name="nome" placeholder="site">

    </div>
    <div class="input-group form-group">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
        </div>
        <input autocomplete="off" type="text" class="form-control" id="email" name="nome" placeholder="email">

    </div>
    <div class="form-group">
        <input type="submit" value="Register" class="btn float-right login_btn" id="btnRegister" onclick="registrarEmpresa()">
    </div>
    <div class="form-group">
        <input type="submit" value="Voltar" class="btn float-right login_btn" id="btnVoltarLogin">
    </div>
</form>
    `;
    let element = document.getElementById('espacoTroca').innerHTML = newElement;
}

function registerEstudante(){
    event.preventDefault();
    console.log(senhaEstudante.value)
    let params = `username=${usuario.value}&password=${senhaEstudante.value}&prenome=${prenome.value}&email=${email.value}&cpf=${cpf.value}&periodo=${periodo.value}&telefone=${telefone.value}&curso=${cursoEstudante.value}`;
    let xhr = new XMLHttpRequest();
    xhr.open('POST', `http://localhost:6543/estudante/add?${params}`);
    xhr.onload = function(){
        if(xhr.status == "200"){
            alert("Enviado com sucesso!");
            window.location.reload();
        }else alert("Algum error ocorreu!")
    }
    xhr.send(null);
}

function registrarEmpresa(){
    event.preventDefault();
    let params = `cnpj=${Cnpj.value}&email=${email.value}&nome=${nomeEmpresa.value}&descricao=${descricao.value}&site=${site.value}&senha=${senhaEmpregador.value}`;
    let xhr = new XMLHttpRequest();
    xhr.open('POST', `http://localhost:6543/empregador/add?${params}`);
    xhr.onload = function(){
        if(xhr.status == "200"){
            alert("Enviado com sucesso!");
            window.location.reload();
        }else alert("Algum error ocorreu!")
    }
    xhr.send(null);
}


function obtemDadosAJAX(){
    //executar chamada AJAX para a API do JSONSERVER
    let xhr = new XMLHttpRequest();
    xhr.onload = verificaLoginEstudante;
    xhr.open('GET', `http://localhost:6543/estudante/get?usuario=${nome.value}`);
    xhr.send();
}

function obterDadosEmpregador(){
    //executar chamada AJAX para a API do JSONSERVER
    let xhr = new XMLHttpRequest();
    xhr.onload = verificaLoginEmpregador;
    xhr.open('GET', `http://localhost:6543/empregador/get?cnpjEmpregador=${nome.value}`);
    xhr.send();
}

function verificaLoginEstudante(dados){
    dados = JSON.parse(this.responseText);
    if(dados[0].periodo != 0){
        for(i = 0; i < dados.length; i++){
            let usuario = dados[i].usuario;
            let idAluno = dados[i].id;
            let nomeAluno = dados[i].prenome;
            let senhaAluno = dados[i].senha;
            let email = dados[i].email;
            let periodo = dados[i].periodo;
            let curso = dados[i].curso;
            let tel = dados[i].telefone;
            if(nome.value == usuario && senha.value == senhaAluno){
                loginAluno(idAluno, usuario, 1, nomeAluno, email, periodo, curso, tel);
            }
        }
    }else obterDadosEmpregador();
}

function verificaLoginEmpregador(dados){
    dados = JSON.parse(this.responseText);
    if(dados[0].cnpj != null){
        for(i = 0; i < dados.length; i++){
            let cnpj = dados[i].cnpj;
            let id = dados[i].id;
            let nomeEmpregador = dados[i].nome;
            let senhaEmpregador = dados[i].senha;
            let email = dados[i].email;
            let site = dados[i].site;
            let descricao = dados[0].descricao;
            console.log(id)
            if(nome.value == cnpj && senha.value == senhaEmpregador){
                loginEmpregador(id, 2, nomeEmpregador, email, site, descricao, cnpj);
            }
        }
    }else console.log("deu ruim");
}

function loginAluno(idAluno, usuarioTxt, tipo, nomeTxt, emailTxt, periodoTxt, cursoTxt, telTxt){
    let user = {usuario:usuarioTxt, tipoUser:tipo, id:idAluno, nome:nomeTxt, email:emailTxt, periodo:periodoTxt, curso:cursoTxt, tel:telTxt};
    localStorage.setItem('user', JSON.stringify(user));
    window.location.replace("./../html/initialScreen.html");
}

function loginEmpregador(idEmpregador, tipo, nomeTxt, emailTxt, siteTxt, descricaoTxt, cnpjTxt){
    let user = {cnpj:cnpjTxt, tipoUser:tipo, id:idEmpregador, nome:nomeTxt, email:emailTxt, descricao:descricaoTxt, site:siteTxt};
    localStorage.setItem('user', JSON.stringify(user));
    window.location.replace("./../html/initialScreen.html");
}


//API - requisição e resposta
imageHandler = (e) => {
    console.log("ok");
    const reader = new FileReader();
    
    reader.readAsDataURL(e.target.files[0]);

    let formData = new FormData();
    formData.append("file", e.target.files[0]);
    let url = "http://127.0.0.1:3002/api/analyze/";
    axios
      .post(url, formData, {
        headers: {
          "content-type": "application/pdf",
          "Ocp-Apim-Subscription-Key": "267a6513a34b45bb8c045a7099016532",
        },
      })
      .then((res) => {
        console.log(res);
        let rec = res.data.output[0].fields;
      });
      alert("Obtendo informações! Espere um momento e depois clique em PDF para preencher os campos.");
  };

retrurnJson = () => {
  console.log("teste");
    let url = "http://127.0.0.1:3002/api/json/";
    axios
      .get(url)
      .then((res) => {
        console.log(res);
        let rec = res.data;
        render(rec);
    });
  };
 
function render(rec){
    var nome = "";
    var sobrenome = "";
    var email = "";
    var periodo = "";
    var curso = "";
    var telefone = "";

    for(var i in rec.output){
      if (rec.output[i].Nome){
        nome = rec.output[i].Nome;
      }if (rec.output[i].Sobrenome){
        sobrenome = rec.output[i].Sobrenome;
      }if (rec.output[i].Email){
        email = rec.output[i].Email;
      }if (rec.output[i].Periodo){
        periodo = rec.output[i].Periodo;
      }if (rec.output[i].Curso){
        curso = rec.output[i].Curso;
      }if (rec.output[i].Telefone){
        telefone = rec.output[i].Telefone;
      }
    }
    //rec.output[5].Nome
    $("#prenome").val(nome + " " + sobrenome);
    $("#email").val(email);
    $("#periodo").val(periodo);
    $("#cursoEstudante").val(curso);
    $("#telefone").val(telefone);
}