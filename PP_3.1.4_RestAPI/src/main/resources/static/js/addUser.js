const adminRoleUrl = "http://localhost:8080/api/roles/all";
const userRoleUrl = "http://localhost:8080/api/roles/2"

const userFirstNameAdd = document.getElementById('userNameAdd')
const userLastNameAdd = document.getElementById('userSurnameAdd')
const userAgeAdd = document.getElementById('userAgeAdd')
const userUsernameAdd = document.getElementById('userUsernameAdd')
const userPasswordAdd = document.getElementById('userPasswordAdd')

function Get(rolesUrl){
    var httpRequest = new XMLHttpRequest();
    httpRequest.open("GET",rolesUrl,false);
    httpRequest.send(null);
    return httpRequest.responseText;
}

var adminRole = JSON.parse(Get(adminRoleUrl));
var userRole = [ JSON.parse(Get(userRoleUrl)) ] ;

const addForm = document.querySelector('#addForm')
addForm.addEventListener('submit', (e) => {
        e.preventDefault()

    var selectedRole = addForm.addRole.options.selectedIndex
        if (selectedRole == 0) {
            role = adminRole
        } else role = userRole

        fetch(url, {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({
                name: userFirstNameAdd.value,
                surname: userLastNameAdd.value,
                age: userAgeAdd.value,
                username: userUsernameAdd.value,
                password: userPasswordAdd.value,
                roles: role
            })
        }).then(response => response.json()).then(() => location.reload())
    }
)
