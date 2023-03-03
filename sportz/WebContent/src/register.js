
const Name = document.getElementById('name');
const phone = document.getElementById('phone');
const email = document.getElementById('email');
const address = document.getElementById('address');
const password = document.getElementById('password');

const form = document.querySelector('form').addEventListener('submit', (e) => {
    e.preventDefault();

    const passd = btoa(password.value);
     //const base64data = Utilities.base64Encode(password.value, Utilities.Charset.UTF_8);
    console.log(passd);



      //axios.post('http://localhost:8321/sportz/Register', {
	axios.post('http://localhost:8080/sportz/Register',{
        "Access-Control-Allow-Origin":"*"
    }, {
        Name: Name.value,
        phone: phone.value,
        email: email.value,
        address: address.value,
        password: passd

    }).then((response) => {

        console.log(response);
        // console.log(response);

    });

})