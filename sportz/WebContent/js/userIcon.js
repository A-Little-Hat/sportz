const logout = document.getElementById("logout")
const register = document.getElementById("register")
const profile = document.getElementById("profile")

register.style.display = 'none';
logout.style.display = 'none';
profile.style.display = 'none';

function getCookie(cname) {
  let name = cname + "=";
  let ca = document.cookie.split(';');
  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}
(function checkCookie() {
  let username = getCookie("email");
  if (username != "") {


    register.style.display = 'none';
    logout.style.display = 'block';
    profile.style.display = 'block';
  } else {

    logout.style.display = "none"
    profile.style.display = "none"
    register.style.display = "block"

    // alert("not logged in")
  }
})()