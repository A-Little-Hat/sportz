
const productTable = document.getElementById('product-table')
const orderTable = document.getElementById('order-table')
const customerTable = document.getElementById('customer-table')
const insertData = document.getElementById('insert')

const Insert = document.getElementById('Insert')
const Products = document.getElementById('Products')
const Customers = document.getElementById('Customers')
const Orders = document.getElementById('Orders')

Insert.addEventListener('click', () => {

	insertData.style.display = 'block'
	productTable.style.display = 'none'
	orderTable.style.display = 'none'
	customerTable.style.display = 'none'

})
Products.addEventListener('click', () => {

	productTable.style.display = 'block'
	insertData.style.display = 'none'
	orderTable.style.display = 'none'
	customerTable.style.display = 'none'

})
Orders.addEventListener('click', () => {

	orderTable.style.display = 'block'
	insertData.style.display = 'none'
	productTable.style.display = 'none'
	customerTable.style.display = 'none'

})
Customers.addEventListener('click', () => {

	customerTable.style.display = 'block'
	insertData.style.display = 'none'
	productTable.style.display = 'none'
	orderTable.style.display = 'none'

})


productTable.style.display = 'none'
//orderTable.style.display = 'none'
customerTable.style.display = 'none'
insertData.style.display = 'none'



const links = document.getElementsByClassName('nav-link');

Array.from(links).forEach(element => {

	element.addEventListener('click', () => {
		document.getElementsByClassName('active')[0].classList.remove('active');
		element.classList.add('active');
	})
})



const getCustomerData = async () => {
	let totalUser = 0
	const {
		data: { products },
	} = await axios.get("http://localhost:8080/sportz/SendUser");

	let items = `
    <thead>
                  <tr>
                    <th>Customer name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Status</th>
                  </tr>
                </thead>
    
    `;

	products && products.map((element) => {
		totalUser = totalUser + 1;
		const imgurl = `https://api.dicebear.com/5.x/lorelei/png?seed=${element.email}`
		items += `

        <tr>
        <td class="member">
          <figure><img src="${imgurl}" /></figure>
          <div class="member-info">
            <p>${element.name}</p>
            <p>India</p>
          </div>
        </td>
        <td>
          <p>${element.email}</p>
        </td>
        <td>
          <p class="text-ok">${element.phone}</p>
        </td>
        <td>
          <p>${element.address}</p>

        </td>
        <td class="status">
          <span class="status-text status-green">Active user</span>
        </td>
      </tr>
        `

	})
	document.getElementById('user-tbody').innerHTML = items;
	document.getElementById('total-customers-count').innerText = totalUser;
	console.log(products[0], products[1]);
}

getCustomerData();

const getOrderData = async () => {
	let totalOrder = 0
	const {
		data: { products },
	} = await axios.get("http://localhost:8080/sportz/SendOrder");

	let items = `<thead>
                    <tr>
                    <th>Order ID</th>
                    <th>Customer Email</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Total</th>
                    <th>Products</th>
                    </tr>
                </thead>`;

	products && products.map((element) => {
		let imgurl = `https://api.dicebear.com/5.x/icons/png?seed=${element.orderId}`
		totalOrder = totalOrder + 1;
		let total = 0
		let prodNameList = ""
		console.log(JSON.parse(element.product))
		JSON.parse(element.product).map((prodItem) => {
			prodNameList += `<p>${prodItem.product_name}</p>`
			total += prodItem.quantity * prodItem.product_price
		})
		console.log(total)
		items += `
            
                    <tr>
                    <td class="member">
                      <figure><img src="${imgurl}" /></figure>
                        <div class="member-info">
                        <p>${element.orderId}</p>
                      </div>
                    </td>
                    <td>
                      <p>${element.userEmail}</p>
                    </td>
                    <td>
                      <p class="text-ok">${element.orderPhoneNumber}</p>
                    </td>
                    <td>
                      <p>${element.orderAddress}</p>
            
                    </td>
                    <td>
                      <p>${total}</p>
            
                    </td>
                    <td class="status">
                      <span class="status-text status-green">${prodNameList}</span>
                    </td>
                  </tr>
                    `
		total = 0
		prodNameList = ""
	})

	document.getElementById('order-tbody').innerHTML = items;
	document.getElementById('total-orders-count').innerText = totalOrder;



	console.log(products[0], products[1]);
}
getOrderData();

const getProductData = async () => {
	let totalProduct = 0
	const {
		data: { products },
	} = await axios.get("http://localhost:8080/sportz/SendProduct");

	let items = `<thead>
                    <tr>
                    <th>Product Name</th>
                    <th>Product ID</th>
                    <th>Product Desc</th>
                    <th>Category</th>
                    <th>Quantity</th>
                    </tr>
                </thead>`;

	products && products.map((element) => {
		totalProduct = totalProduct + 1
		const imgurl = `https://api.dicebear.com/5.x/lorelei/png?seed=${element.userEmail}`
		items += `
                    <tr>
                    <td class="member">
                    <figure><img src="${element.product_image}" /></figure>
                        <div class="member-info">
                        <p>${element.product_name}</p>
                        <p>${element.product_id}</p>
                    </div>
                    </td>
                    <td>
                    <p>${element.product_id}</p>
                    </td>
                    <td>
                    <p class="text-ok">${element.product_description}</p>
                    </td>
                    <td>
                    <p>${element.product_category}</p>

                    </td>
                    <td class="status">
                    <span class="status-text status-green">${element.count}</span>
                    </td>
                </tr>
                    `

	})

	document.getElementById('product-tbody').innerHTML = items;
	document.getElementById('total-products-count').innerText = totalProduct;

	console.log(products[0], products[1]);
}
getProductData();

const pdesc = document.getElementById('pdesc')
const category = document.getElementById('category')
const pprice = document.getElementById('pprice')
const purl = document.getElementById('purl')
const pname = document.getElementById('pname')
const pcount = document.getElementById('pcount')


