const editModal = new bootstrap.Modal(document.getElementById('editModal'))
const deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'))

const editForm = document.querySelector('#editForm')
const deleteForm = document.querySelector('#deleteForm')

const userIdEdit = document.getElementById('idEdit')
const userNameEdit = document.getElementById('nameEdit')
const userSurnameEdit = document.getElementById('surnameEdit')
const userAgeEdit = document.getElementById('ageEdit')
const userUsernameEdit = document.getElementById('usernameEdit')
const userPasswordEdit = document.getElementById('passwordEdit')

const userIdDelete = document.getElementById('idDelete')
const userNameDelete = document.getElementById('nameDelete')
const userSurnameDelete = document.getElementById('surnameDelete')
const userAgeDelete = document.getElementById('ageDelete')
const userUsernameDelete = document.getElementById('usernameDelete')

const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}

on(document, "click", ".btn-info", e => {

    const data = e.target.parentNode.parentNode
    const idFormEdit = data.firstElementChild.innerHTML
    const nameFormEdit = data.children[1].innerHTML
    const surnameFormEdit = data.children[2].innerHTML
    const ageFormEdit = data.children[3].innerHTML
    const usernameFormEdit = data.children[4].innerHTML
    const passwordForm = data.children[5].innerHTML
    userIdEdit.value = idFormEdit
    userNameEdit.value = nameFormEdit
    userSurnameEdit.value = surnameFormEdit
    userAgeEdit.value = ageFormEdit
    userUsernameEdit.value = usernameFormEdit
    userPasswordEdit.value = passwordForm;
    editModal.show()
})

editForm.addEventListener('submit', (e) => {

    e.preventDefault()
    var selectedRole = editForm.roleAdmin.options.selectedIndex
    if (selectedRole == 0) {
        role = adminRole
    } else role = userRole
        fetch(url, {
            method: 'PUT',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({
                id: userIdEdit.value,
                name: userNameEdit.value,
                surname: userSurnameEdit.value,
                age: userAgeEdit.value,
                username: userUsernameEdit.value,
                password: userPasswordEdit.value,
                roles: role
            })
        }).then(response => response.json()).then(response => location.reload())
        editModal.hide()
    }
)

on(document, "click", ".btn-danger", e => {

    const data = e.target.parentNode.parentNode
    const idForm = data.firstElementChild.innerHTML
    const nameForm = data.children[1].innerHTML
    const surnameForm = data.children[2].innerHTML
    const ageForm = data.children[3].innerHTML
    const usernameForm = data.children[4].innerHTML
    userIdDelete.value = idForm
    userNameDelete.value = nameForm
    userSurnameDelete.value = surnameForm
    userAgeDelete.value = ageForm
    userUsernameDelete.value = usernameForm
    deleteModal.show()
})

deleteForm.addEventListener('submit', (e) => {

        e.preventDefault()
        const idToDelete = userIdDelete.value

        fetch(url+idToDelete, {
            method: 'DELETE',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({})
        }).then(response => response.json()).then(response => location.reload())
        deleteModal.hide()
        document.location.reload();
    }
)
