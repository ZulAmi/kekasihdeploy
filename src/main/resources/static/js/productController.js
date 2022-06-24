//Doing a Product web app, in product page to 
//display the name, description, imageUrl, price, ..., ...,.....,....


const createHTMLList = (index, name, description, imageURL, price) =>

`<div class="col-lg-6">
    <div class="card mb-3">
        <div class="row g-0">
            <div class="col-md-5 d-flex justify-content-center align-items-center">
                <img src=${imageURL}
                    class="card-img-top" alt="...">
            </div>
            <div class="container col-md-6 ">
                <div class="card-body row align-items-center">
                    <h5 class="card-title">${name}</h5>
                    <p class="card-text">${description}</p>
                    <a id="${index}" href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#productModal">More</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
`;


function displayProductDetails(item)
{
    document.querySelector("#modalName").innerText = item.name;
    document.querySelector("#modalImg").src = item.imageUrl;
    document.querySelector("#modalPrice").innerText = item.price;
    document.querySelector("#modalDesc").innerText = item.description;

}


class ProductsController 
{
    constructor()
    {

            this.domainURL_Dev = "http://localhost:8090";
            this.domainURL_Prod = "https://kekasih.herokuapp.com";

            this.addItemAPI = this.domainURL_Prod + "/item/add";
            this.allItemAPI = this.domainURL_Prod + "/item/all";

        this._items = [];
    }


    addItem(name, description, imageUrl, price, imageObject)
    {
          var productController = this;
                const formData = new FormData();
                formData.append('name', name);
                formData.append('description', description);
                formData.append('imageUrl', imageUrl);
                formData.append('price', price);
                formData.append('imagefile',imageObject);

                //GET method

               fetch(this.addItemAPI, {
                     method: 'POST',
                     body: formData
                     })
                     .then(function(response) {
                         console.log(response.status);
                         if (response.ok) {
                             alert("Successfully Added Product!")
                         }
                         else{
                         throw Error (response.statusText);
                         }
                     })
                     .catch((error) => {
                         console.error('Error:', error);
                         alert("Error adding item to Product")
                     });

    }



    //Based on the item fetched from the displayItem method and show the products in the page
    renderProductPage()
    {
        let productHTMLList = [];


        for (let i=0; i<this._items.length; i++)
        {
            const item = this._items[i];            //assign the individual item to the variable

            const productHTML = createHTMLList(i, item.name, item.description, item.imageUrl);

            productHTMLList.push(productHTML);

        }


        const pHTML = productHTMLList.join('\n');

        document.querySelector('#row1').innerHTML = pHTML;


        for (let i=0; i<this._items.length; i++)
        {
            const item = this._items[i];
            document.getElementById(i).addEventListener("click", function() { displayProductDetails(item);} );
        }

    }


//This method if to fetch data from database
    displayItem()
    {

            let productController = this;
            productController._items = [];


            fetch(this.allItemAPI)
                .then((resp) => resp.json())
                .then(function(data) {
                    console.log("2. receive data")
                    console.log(data);
                    data.forEach(function (item, index) {

                        const itemObj = {
                            id: item.id,
                            name: item.name,
                            description: item.description,
                            imageUrl: item.imageUrl,
                            price: item.price
                       };
                        productController._items.push(itemObj);
                  });

                  productController.renderProductPage();

                })
                .catch(function(error) {
                    console.log(error);
                })

    }
}   //End of ProductsController class
