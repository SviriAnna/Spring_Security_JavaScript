document.getElementById('modalDelete').addEventListener('show.bs.modal', function (event) {
    const button = event.relatedTarget;
    const IdUser = button.getAttribute('data-user-id');
    const url = `http://localhost:8080/api/admin/users/${IdUser}`;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            document.getElementById('delete_id').value = data.id;
            document.getElementById('delete_username').value = data.username;
            document.getElementById('delete_name').value = data.name;
            document.getElementById('delete_lastname').value = data.lastname;
            document.getElementById('delete_age').value = data.age;
            populateRolesSelect1()
        })

    document.getElementById('deleteForm').addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = new FormData(event.target);
        const data = Object.fromEntries(formData.entries());
        const roles = getSelectedRoles1();
        data.roles = roles;

        const id = document.getElementById('delete_id').value;
        data.id = id
        const url = `http://localhost:8080/api/admin/users/${id}`;

        fetch(url, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(() => {
                document.getElementById('modalDelete')
                const modalElement = document.getElementById('modalDelete');
                const modalInstance = bootstrap.Modal.getInstance(modalElement); //подключить ноде.джиэс
                modalInstance.hide();
                getUsersInfo();
            })

    });
});

function populateRolesSelect1() {
    const url = 'http://localhost:8080/api/admin/roles';
    fetch(url)
        .then(response => response.json())
        .then(roles => {
            console.log(roles)
            const selectElement = document.getElementById('delete_role');
            selectElement.innerHTML = '';
            roles.forEach(role => {
                const option = document.createElement('option');
                option.value = role.id;
                option.text = role.name.replace('ROLE_', '');
                selectElement.appendChild(option);
            });
        })
}

function getSelectedRoles1() {
    const rolesSelect = document.getElementById('delete_role');
    const selectedRoles = Array.from(rolesSelect.selectedOptions).map(option => {
        return {
            id: option.value,
            role: 'ROLE_' + option.text
        };
    });
    return selectedRoles;
}