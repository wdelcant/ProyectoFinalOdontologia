window.addEventListener('load', function () {


    (function(){

        //con fetch invocamos a la API de odontologos con el método GET
        //nos devolverá un JSON con una colección de odontologos

        String
        let token;
        token = localStorage.getItem('jwtToken');

            const url = '/odontologos';
            const settings = {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json', // Adjust the content type as needed
                },
            }


            fetch(url, settings)
                .then(response => response.json())
                .then(data => {
                    //recorremos la colección de odontologos del JSON
                    for (odontologo of data) {
                        //por cada odontologo armaremos una fila de la tabla
                        //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la odontologo
                        var table = document.getElementById("odontologoTable");
                        var odontologoRow = table.insertRow();
                        let tr_id = 'tr_' + odontologo.id;
                        odontologoRow.id = tr_id;

                        //por cada odontologo creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
                        //dicho boton invocara a la funcion de java script deleteByKey que se encargará
                        //de llamar a la API para eliminar una odontologo
                        let deleteButton = '<button' +
                            ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                            ' type="button" onclick="deleteBy(' + odontologo.id + ')" class="btn btn-danger btn_delete">' +
                            '&times' +
                            '</button>';

                        //por cada odontologo creamos un boton que muestra el id y que al hacerle clic invocará
                        //a la función de java script findBy que se encargará de buscar la odontologo que queremos
                        //modificar y mostrar los datos de la misma en un formulario.
                        let updateButton = '<button' +
                            ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                            ' type="button" onclick="findBy(' + odontologo.id + ')" class="btn btn-info btn_id">' +
                            odontologo.id +
                            '</button>';

                        //armamos cada columna de la fila
                        //como primer columna pondremos el boton modificar
                        //luego los datos de la odontologo
                        //como ultima columna el boton eliminar
                        odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                            '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                            '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                            '<td class=\"td_matricula\">' + odontologo.matricula + '</td>' +
                            '<td>' + deleteButton + '</td>';

                    }


                })


    })

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/odontologoList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })


})

window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará de la nueva odontologo
    const formulario = document.querySelector('#add_new_odontologo');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        //creamos un JSON que tendrá los datos de la nueva película
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,

        };
        //invocamos utilizando la función fetch la API odontologos con el método POST que guardará
        //la película que enviaremos en formato JSON
        const url = '/odontologos';

        const formDataJson =  JSON.stringify(formData);

        let token = localStorage.getItem('jwtToken');
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`,
            },
            body: formDataJson
        }

        console.log(JSON.stringify(formData));

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                //Si no hay ningun error se muestra un mensaje diciendo que la odontologo
                //se agrego bien

                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Odontologo agregado </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";


            })
            .catch(error => {
                alert(error)
                //Si hay algun error se muestra un mensaje diciendo que la odontologo
                //no se pudo guardar y se intente nuevamente
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                //se dejan todos los campos vacíos por si se quiere ingresar otra odontologo
            })
    });

    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#matricula').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/odontologoList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});


window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la odontologo
    const formulario = document.querySelector('#update_odontologo_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        let odontologoId = document.querySelector('#id').value;

        //creamos un JSON que tendrá los datos de la película
        //a diferencia de una odontologo nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const formData = {
            id: document.querySelector('#id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,

        };

        //invocamos utilizando la función fetch la API odontologos con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = '/odontologos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url,settings)
            .then(response => {
                alert(response.status)
                if(response.ok){
                    console.log("Respuesta ok")
                    let successAlert = '<div class="alert alert-success alert-dismissible">' +
                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                        '<strong></strong> PAciente actualizado </div>'
                    document.querySelector('#response').innerHTML = successAlert;
                    document.querySelector('#response').style.display = "block";
                    response.json();
                    window.location.href = "http://localhost:8081/";
                }else if(response.status === 400){
                    alert("Error - No se pudo actualizar la persona")
                }

            })
            .catch(error => {
                console.log(error);
                alert("Error - No se pudo actualizar elmpaciencasdg")
            });

    })
})

//Es la funcion que se invoca cuando se hace click sobre el id de una odontologo del listado
//se encarga de llenar el formulario con los datos de la odontologo
//que se desea modificar
function findBy(id) {
    const url = '/odontologos'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {

            if(response.ok){
                let odontologo = data;
                document.querySelector('#id').value = odontologo.id;
                document.querySelector('#nombre').value = odontologo.nombre;
                document.querySelector('#apellido').value = odontologo.apellido;
                document.querySelector('#matricula').value = odontologo.matricula;
                //el formulario por default esta oculto y al editar se habilita
                document.querySelector('#div_odontologo_updating').style.display = "block";
            }else if(response.status = 403){
                console.log(response)
                alert(response)
            }

        }).catch(error => {
        alert("Error: " + error);
    })
}

function deleteBy(id)
{
    //con fetch invocamos a la API de odontologos con el método DELETE
    //pasandole el id en la URL
    console.log("eliminando odontologo " + id)
    const url = '/odontologos/'+ id;
    let token = localStorage.getItem('jwtToken');
    const settings = {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
        },
    }
    fetch(url,settings)
        .then(response =>{

            if(response.ok){
                response.json();
                //borrar la fila de la odontologo eliminada
                let row_id = "#tr_" + id;
                document.querySelector(row_id).remove();
            }else {
                console.log(response)
                alert(response.status)
            }

        }).catch(error =>alert("Error al borrar"));




}