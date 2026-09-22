console.log("this is script file");

const toggleSidebar = () => {
    if ($(".sidebar").is(":visible")) {
        //true
        //Close it
        $(".sidebar").css("display", "none");
        $(".content").css("margin-left", "0%");
    } else {
        //false
        //Show it
        $(".sidebar").css("display", "block");
        $(".content").css("margin-left", "20%");
    }
};

const search = () => {
    // console.log("searching...");
    let query = $("#search-input").val();

    if (query == "") {
        $(".search-result").hide();
    } else {
        //search
        console.log(query);

        // Sending resuest to server
        let url = `http://localhost:8282/search/${query}`;

        fetch(url)
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                //data...
                // console.log(data); //to check in inspect console
                let text = `<div class='list-group'>`;

                data.forEach((contact) => {
                    text += `<a href='/user/contact/${contact.cId}' class='list-group-item list-group-item-action'> ${contact.name} </a>`
                })

                text += `</div>`;

                $(".search-result").html(text);
                $(".search-result").show();
            });
    }
};

//first request-o server to create order

const paymentStart = () => {
    console.log("paymentstarted..");
    let amount = $("#payment_field").val();
    console.log(amount);
    if (amount == '' || amount == null) {
        //alert("amount is required!!");
        swal("Failed !!", "Enter Amount!!", "error");
        return;
    }
    //code...
    //we willl use ajax to send request to server to create order- jquery 
    $.ajax(
        {
            url: '/user/create_order',
            data: JSON.stringify({ amount: amount, info: 'order_request' }),
            contentType: 'application/json',
            type: 'POST',
            dataType: 'json',
            success: function (response) {
                //invoked where success
                console.log(response)
                if (response.status == "created") {
                    //open pament form
                    let options = {
                        key: 'rzp_test_u85DaHtW4FEAe8',
                        amount: response.amount,
                        currency: 'INR',
                        name: 'Smart Contact Manager',
                        description: 'Donation',
                        Image: "https://media.licdn.com/dms/image/D4D03AQHTztTqCfkx1g/profile-displayphoto-shrink_400_400/0/1698504056569?e=2147483647&v=beta&t=SGCjCW5GrWiepd5tABzIdJCnhYOz7GuoSXQ8e5yH7VQ",
                        order_id: response.id,
                        handler: function (response) {
                            console.log(response.razorpay_payment_id);
                            console.log(response.razorpay_order_id);
                            console.log(response.razorpay_signature);
                            console.log("payment successful!!");
                            //alert("congrates!! Payment successful!!");
                            updatePaymentOnServer(
                                response.razorpay_payment_id,
                                response.razorpay_order_id,
                                "paid"
                            );
                        },
                        prefill: {
                            name: "",
                            email: "",
                            contact: "",
                        },
                        notes: {
                            address: " Learn Coding with Stephen",
                        },
                        theme: {
                            color: "#3399cc"
                        },
                    };
                    let rzp = new Razorpay(options);
                    rzp.on('payment.failed', function (response) {
                        console.log(response.error.code);
                        console.log(response.error.description);
                        console.log(response.error.source);
                        console.log(response.error.step);
                        console.log(response.error.reason);
                        console.log(response.error.metadata.order_id);
                        console.log(response.error.metadata.payment_id);
                        //alert("Oops payment failed !!");
                        swal("Failed !!", "Oops payment failed !!", "error");
                    });

                    rzp.open();

                }

            },
            error: function (error) {
                //invoked when error
                console.log(error)
                swal("Something went wrong !!", "Try Again!!", "error");
                //alert("something went wrong!!")
            },
        },
    );
};

//
function updatePaymentOnServer(payment_id, order_id, status) {
    $.ajax(
        {
            url: '/user/update_order',
            data: JSON.stringify({
                payment_id: payment_id,
                order_id: order_id,
                status: status,
            }),
            contentType: 'application/json',
            type: 'POST',
            dataType: 'json',
            success: function (response) {
                swal("Good job!", "congrates!! Payment successful!!", "success");
            },
            error: function (error) {
                swal("Update Failed !!", "Your paymnt is successful, but we did not get on server. We will contact you ASAP", "error");
            },
        });
}