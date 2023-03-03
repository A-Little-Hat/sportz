
const insert = document.getElementById('products')

const getData = async () => {


    const { data: { products } } = await axios.post('http://localhost:8080/sportz/Products');

    let items = '';
    console.log(products[0], products[1]);


    products && products.map((item) => {

        items += `  
        <div class="col-12 col-md-4 col-lg-3 mb-5">
        <a class="product-item" href="../cricket/bat.html">
          <img src="../images/pngegg.png" class="img-fluid product-thumbnail"  style="height: 300px;">
          <h3 class="product-title">${item.product_name}</h3>
          <strong class="product-price">₹${item.product_price}</strong>
          <p>${item.product_description}</p>
          <span class="icon-cross">
            <img src="./images/cricket/${item.product_name}.png" class="img-fluid">
          </span>
        </a>
      </div>
        `

    })
    insert.innerHTML = items;

}

getData();