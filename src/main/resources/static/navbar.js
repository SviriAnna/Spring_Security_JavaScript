async function getNavInfo() {
    fetch('http://localhost:8080/user/info')
        .then(res => res.json())
        .then(user => getInfo(user))
}

function getInfo(user) {
    document.getElementById('username_in_page_header').textContent = user.username;
    document.getElementById('roles_in_page_header').textContent = getRolesString(user.roles);
}

getNavInfo()