fetch(allInfoUrl).then( res => {
    res.json().then( data => {
        document.getElementById("adminPage").innerHTML = `
            <b>${data.username}</b>
            <span style="font-family: sans-serif">with roles:</span>
            <span style="font-family: sans-serif">${getRolesOfAllUsers(urlRoles + data.id)}</span>
        `;
    })
})