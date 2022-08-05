const allInfoUrl = "http://localhost:8080/api/users/all_information_about_users";

fetch(allInfoUrl).then( res => {
    res.json().then( data => {
        document.getElementById("userInfo").innerHTML = `   
            <tr>
                <td>${data.id}</td>
                <td>${data.name}</td>
                <td>${data.surname}</td>
                <td>${data.age}</td>
                <td>${data.username}</td>
                <td>${getRolesOfAllUsers(urlRoles + data.id)}</td>
            </tr>
        `;
    })
})

