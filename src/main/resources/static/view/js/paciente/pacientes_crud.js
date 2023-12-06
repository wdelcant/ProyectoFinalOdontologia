
window.addEventListener('load', function () {
    (function(){

        //con fetch invocamos a la API de pacientes con el método GET
        //nos devolverá un JSON con una colección de pacientes
        String
        let token;
        token = localStorage.getItem('jwtToken');

        const url = '/pacientes';
        const settings = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json', // Adjust the content type as needed
            },
        }
        fetch(url,settings)
            .then(response => response.json())
            .then(data => {
                //recorremos la colección de pacientes del JSON
                for(paciente of data){
                    //por cada paciente armaremos una fila de la tabla
                    //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la paciente
                    var table = document.getElementById("pacienteTable");
                    var pacienteRow =table.insertRow();
                    let tr_id = 'tr_' + paciente.id;
                    pacienteRow.id = tr_id;

                    let deleteButton = '<button' +
                        ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                        ' type="button" onclick="deleteBy('+paciente.id+')" class="btn btn-danger btn_delete">' +
                        '&times' +
                        '</button>';

                    let updateButton = '<button' +
                        ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                        ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                        paciente.id +
                        '</button>';

                    // Agrega una celda para el apellido
                    let apellidoCell = '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>';

                    pacienteRow.innerHTML = '<td>' + updateButton + '</td>' +
                        '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                        apellidoCell + // Agrega la celda del apellido aquí
                        '<td class=\"td_dni\">' + paciente.dni + '</td>' +
                        '<td class=\"td_fechaAlta\">' + (paciente.fechaAlta ? new Date(paciente.fechaAlta).toLocaleDateString() : 'Fecha no disponible') + '</td>' +
                        '<td>' + deleteButton + '</td>';

                };

            })
    })

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/pacienteList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })


})

window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará de la nueva paciente
    const formulario = document.querySelector('#add_new_paciente');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        //creamos un JSON que tendrá los datos de la nueva película
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            domicilio: {
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#regiones').value,
                provincia: document.querySelector('#comunas').value
            }


        };
        //invocamos utilizando la función fetch la API pacientes con el método POST que guardará
        //la película que enviaremos en formato JSON
        const url = '/pacientes';

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
                //Si no hay ningun error se muestra un mensaje diciendo que la paciente
                //se agrego bien

                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Paciente Agregado </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";


            })
            .catch(error => {
                alert(error)
                //Si hay algun error se muestra un mensaje diciendo que la paciente
                //no se pudo guardar y se intente nuevamente
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                //se dejan todos los campos vacíos por si se quiere ingresar otra paciente
            })
    });


    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#dni').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/pacienteList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});


window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la paciente
    const formulario = document.querySelector('#update_paciente_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        let pacienteId = document.querySelector('#id').value;

        //creamos un JSON que tendrá los datos de la película
        //a diferencia de una paciente nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const formData = {
            id: document.querySelector('#id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            domicilio: {
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#regiones').value,
                provincia: document.querySelector('#comunas').value
            }

        };

        //invocamos utilizando la función fetch la API pacientes con el método PUT que modificará
        //el paciente que enviaremos en formato JSON
        const url = '/pacientes';
        let token = localStorage.getItem('jwtToken');
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`,
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
                    window.location.href = "http://localhost:8080/";
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

//Es la funcion que se invoca cuando se hace click sobre el id de una paciente del listado
//se encarga de llenar el formulario con los datos de la paciente
//que se desea modificar
function findBy(id) {
    const url = '/pacientes'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let paciente = data;
            document.querySelector('#id').value = paciente.id;
            document.querySelector('#nombre').value = paciente.nombre;
            document.querySelector('#dni').value = paciente.dni;
            //el formulario por default esta oculto y al editar se habilita
            document.querySelector('#div_paciente_updating').style.display = "block";
        }).catch(error => {
        alert("Error: " + error);
    })
}

function deleteBy(id)
{
    //con fetch invocamos a la API de pacientes con el método DELETE
    //pasandole el id en la URL
    console.log("eliminando Paciente " + id)
    const url = '/pacientes/'+ id;
    let token = localStorage.getItem('jwtToken');
    const settings = {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
        },
    }
    fetch(url,settings)
        .then(response =>{
            response.json();
            //borrar la fila de la paciente eliminada
            let row_id = "#tr_" + id;
            document.querySelector(row_id).remove();
        }).catch(error =>alert("Error al borrar"));




}