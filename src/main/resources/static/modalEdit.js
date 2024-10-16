document.getElementById('modalEdit').addEventListener('show.bs.modal', function (event) {
    const button = event.relatedTarget;
    const userId = button.getAttribute('data-user-id');
    const url = `http://localhost:8080/api/admin/users/${userId}`;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            document.getElementById('edit_id').value = data.id;
            document.getElementById('edit_username').value = data.username;
            document.getElementById('edit_name').value = data.name;
            document.getElementById('edit_lastname').value = data.lastname;
            document.getElementById('edit_age').value = data.age;
            document.getElementById('edit_password').value = data.password;
            populateRolesSelect()
        })

    document.getElementById('editForm').addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = new FormData(event.target);

        const data = Object.fromEntries(formData.entries());

        const roles = getSelectedRoles();
        data.roles = roles;

        const id = document.getElementById('edit_id').value; // Используйте значение из скрытого поля
        data.id = id
        const url = `http://localhost:8080/api/admin/users/${id}`;

        fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(() => {
                document.getElementById('modalEdit')
                const modalElement = document.getElementById('modalEdit');
                const modalInstance = bootstrap.Modal.getInstance(modalElement); //!!
                modalInstance.hide();
                getUsersInfo();
            })
    });
});


function populateRolesSelect() {
    const url = 'http://localhost:8080/api/admin/roles';
    fetch(url)
        .then(response => response.json())
        .then(roles => {
            const selectElement = document.getElementById('edit_role');
            selectElement.innerHTML = '';
            roles.forEach(role => {
                const option = document.createElement('option');
                option.value = role.id;
                option.text = role.name.replace('ROLE_', '');
                selectElement.appendChild(option);
            });
        })
}

function getSelectedRoles() {
    const rolesSelect = document.getElementById('edit_role');
    const selectedRoles = Array.from(rolesSelect.selectedOptions).map(option => {
        return {
            id: option.value,
            role: 'ROLE_' + option.text
        };
    });
    return selectedRoles;
}