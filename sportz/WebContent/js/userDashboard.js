

const orderBtn =  document.getElementById('orderBtn');
const wishlistBtn =  document.getElementById('wishlistBtn');
const order = document.getElementById('orederTableData');
const wishlist = document.getElementById('wish');
const orderTable = document.getElementById('order-table');
const wishlistTable = document.getElementById('wishlistTable');



// user details






orderTable.style.display = 'none'
wishlistTable.style.display = 'none'

orderBtn.addEventListener('click', (e) => {
    console.log("order btn")
    e.preventDefault()
    
    wishlistTable.style.display = 'none'
    orderTable.style.display = 'block'
    console.log("hell2o");


})

wishlistBtn.addEventListener('click', (e) => {
    console.log("hello");
    e.preventDefault()
    orderTable.style.display = 'none'
    wishlistTable.style.display = 'block'

})


const add = (product_id) => {
    axios.post(`http://localhost:8080/sportz/AddToCart?id=${product_id}`);
    alert("Item added to Cart");

};


const del = (product_id) => {
    console.log(product_id);
    axios.post(`http://localhost:8080/sportz/DeleteFromList?id=${product_id}`);
    alert("Item deleted from wishlist");
    getDataFromWishlist();
    //location.reload();
};

const getDataFromWishlist = async () => {
    const {
        data: { products },
    } = await axios.get("http://localhost:8080/sportz/SendWishlistToUserDashBoard");
    let wishlistCount=0;
    let items = "";
    console.log(products[0], products[1]);

    products && products.map((it) => {
        wishlistCount++;
        console.log(it)
        items += ` <tr class="trow">
            <td class="img-name"><img class="picon" src="${it.product_image}" alt="">${it.product_name}</td>
            <td class="desc">${it.product_description}</td>
            <td>Available</td>
            <td>₹${it.product_price}</td>
            <td id="${it.product_id}" onClick="add(${it.product_id})"><svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M6.01 16.136L4.141 4H3a1 1 0 0 1 0-2h1.985a.993.993 0 0 1 .66.235.997.997 0 0 1 .346.627L6.319 5H14v2H6.627l1.23 8h9.399l1.5-5h2.088l-1.886 6.287A1 1 0 0 1 18 17H7.016a.993.993 0 0 1-.675-.248.998.998 0 0 1-.332-.616zM10 20a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm9 0a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm0-18a1 1 0 0 1 1 1v1h1a1 1 0 1 1 0 2h-1v1a1 1 0 1 1-2 0V6h-1a1 1 0 1 1 0-2h1V3a1 1 0 0 1 1-1z" fill="#0D0D0D"/></svg></td>
            <td id="${it.product_id}" onClick="del(${it.product_id})"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16"><path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/> <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/> </svg></td>
          </tr>
          `;

    })

    wishlist.innerHTML = items;
    console.log("wishlistCount : "+ wishlistCount)
    document.getElementById("totalWishlist").innerText = wishlistCount;

    
}


getDataFromWishlist();



const getDataFromOrder = async ()=>{
    // e.preventDefault()
    let items="";
    console.log("getDataFromOrder")
    const {
                data: { products },
            } = await axios.get("http://localhost:8080/sportz/SendOrderToUserDashBoard");
    console.log(products)
    let total=0;
    let orderCount=0;
    let productList = "";
    products && products.map((product)=>{
        orderCount++;
        JSON.parse(product.product).map((element)=>{
            total += (element.product_price * element.quantity);
            productList += `
                <p>${element.product_name}</p>
                `
        })
        console.log(total)
        items += `
        <tr class="trow">
        <td >${product.orderId}</td>
        <td >${product.orderAddress}</td>
        <td >${product.orderPhoneNumber}</td>
        <td >${total}</td>
        <td ><strong>COMPLETED</strong></td>
        <td >${product.pincode}</td>
        <td >${productList}</td>
        
        </tr>
        `
        total=0;
        productList = "";
    })
    document.getElementById("orederTableData").innerHTML = items;
    document.getElementById("totalOrders").innerText = orderCount;
    console.log("orderCount : "+orderCount)
}
getDataFromOrder();


const  getCustomerDetails = async() => {
    const UserName = document.getElementById('UserName');
    const inputEmail = document.getElementById('input-email');
    // const inputUsername = document.getElementById('input-username');
    const inputAddress = document.getElementById('input-address');
    const inputPhone = document.getElementById('input-phone');
    const {
        data: { products },
    } = await axios.get("http://localhost:8080/sportz/SendUserToUserDashBoard");

console.log(products[0]);

UserName.innerText = products[0].name;
inputAddress.innerText = products[0].address;
inputEmail.innerText = products[0].email;
inputPhone.innerText = products[0].phone;

}

getCustomerDetails();