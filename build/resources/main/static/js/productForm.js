const productsControl = new ProductsController();
let storeImage = ""

//When user clicks on 'Save Item', calls API to add items to the database
//Add an 'onsubmit' event listener for productform to add a product
newItemForm.addEventListener('submit', (event) => {
    // Prevent default action. Don't need the form to submit first eg. Form validation.
    //2) use our own fetch method to send data to the back(REST API)
    event.preventDefault();
    // Select the inputs
    const newItemNameInput = document.querySelector('#newItemNameInput');
    const newItemDescription = document.querySelector('#newItemDescription');
    const newItemImageUrl = document.querySelector('#newItemImageFile');
    const newItemPrice = document.querySelector('#newItemPrice');

    /*
        enter the Validation code here
    */

    // Get the values of the inputs - variable names to be same as MySQL columns
    const name = newItemNameInput.value;
    const description = newItemDescription.value;
    // so as not to expose our system file. Safety reason
   const imageUrl = newItemImageUrl.value.replace("C:\\fakepath\\", "");
    const price = newItemPrice.value;

    // Clear the form
    newItemNameInput.value = '';
    newItemDescription.value = '';
    newItemImageUrl.value = '';
    newItemPrice.value = '';

    // Add the task to the task manager

    //after we get value and obj of the form we will call this method to perform the POST HTTP request by calling the rest API provided by backend
    productsControl.addItem(name, description, imageUrl, price, storeImage);

});

// select file input
const input = document.querySelector('#newItemImageFile');

// add event listener
input.addEventListener('change', () => {
    storeImage = input.files[0];
    //this is storing 1 file. with multiple file you can change to the array
    //part 1 is only to store. We have yet to send over to database
});
