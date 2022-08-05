const url = "http://localhost:8080/api/users/";
const urlRoles = "http://localhost:8080/api/roles/about_user/";

function getRolesOfAllUsers(rolesUrlOfUser){
    var httpRequest = new XMLHttpRequest();
    httpRequest.open("GET",rolesUrlOfUser,false);
    httpRequest.send(null);
    return httpRequest.responseText;
}
fetch(url).then(
    res => {
        res.json().then(
            data => {
                if (data.length > 0) {
                    var temp = "";
                    data.forEach((user) => { temp += `   
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <td>${user.age}</td>
                            <td>${user.username}</td>
                            <td style="display: none">${user.password}</td>
                            <td>${getRolesOfAllUsers(urlRoles + user.id)}</td>
                            <td><button class="btn btn-info" id="editButton" 
                            style="border-radius: 3px 3px 3px 3px;">Edit</button></td>
                            <td><button class="btn btn-danger" id="deleteButton" 
                            style="border-radius: 3px 3px 3px 3px;">Delete</button></td>                 
                        </tr>
                    `})
                    document.getElementById("data").innerHTML = temp;
                }
            }
        )
    }
)













