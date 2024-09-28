$(document).ready(function () {
    // $('#modalContent').load("textmod_modal.html");
    // $("html, body").animate({ scrollTop: $("#header").offset().top }, 100);

    $('.textForm-identifierType').prop("checked", true);
    $('#loadinggif-holder').css("visibility", "hidden");
    $('#loadinggif').addClass('d-none');
    monitorTextInput();
    var textmod_Modal = new bootstrap.Modal($('#textmod_homeModal'), {
        keyboard: false
    });


    // $('#textmodepage').on('click', '#removePII', function(){
    //     alert("text tab clicked");
    //     // $("html, body").animate({ scrollTop: $("#header").offset().top }, 100);
    //     // scrollToButton();
    //     var textmod_Modal = new bootstrap.Modal($('#textmod_homeModal'), {
    //         keyboard: false
    //     });
    //
    //     if($('#textinput').val().length < 1){
    //         $('#userAlert').html("Please submit a valid C-CDA XML!");
    //         textmod_Modal.show();
    //         return false;
    //     }
    //     const userInputXML = $('#textinput').val();
    //
    //     if (validateFn(userInputXML)) {
    //         console.log("XML is not valid.");
    //         // alert('inValid XML');
    //         $('#userAlert').html("Please submit a valid C-CDA XML!");
    //         textmod_Modal.show();
    //         return false;
    //     } else {
    //         console.log("XML is  valid.");
    //         // alert('valid XML');
    //     }
    //
    //
    //
    //     event.preventDefault();
    //     submitText();
    //
    //
    //
    // });



});


function timeout(ms){
    console.log("Inside timeout ...");
    return new Promise(res => setTimeout(res, ms));
}

$('#textForm').submit(function (event) {


    var textmod_Modal = new bootstrap.Modal($('#textmod_homeModal'), {
        keyboard: false
    });

    if($('#textinput').val().length < 1){
        $('#userAlert').html("Please submit a valid C-CDA XML!");
        textmod_Modal.show();
        return false;
    }
    const userInputXML = $('#textinput').val();

    if (validateFn(userInputXML)) {
        console.log("XML is not valid.");
        $('#userAlert').html("Please submit a valid C-CDA XML!");
        textmod_Modal.show();
        return false;
    } else {
        console.log("XML is  valid.");
    }

    // check if checkboxes are selected
    let identifierTypes = [];
    $(".textForm-identifierType:checked").each(function() {
        identifierTypes.push($(this).val());
    });
    if(identifierTypes.length<=0){
        alert("No identifier categories selected!");
        return false;
    }


    event.preventDefault();
    submitText();



});


function submitText() {


    var data = new FormData();
    data.append('ccdaXML', $('#textinput').val());
    console.log("XML submitted for processing...");
    $('#loadinggif-holder').css("visibility", "visible");
    $('#loadinggif').removeClass('d-none');


    // let arr = [];
    // $("input:checkbox[name=category]:checked").each(function() {
    //     arr.push($(this).val());
    // });

    // alert($('input[name="category"]:checked').val());
    let identifierTypes = [];
    $(".textForm-identifierType:checked").each(function() {

        identifierTypes.push($(this).val());
    });
    data.append('categories', identifierTypes);

    var progressBar = $('#text-progress_bar');
    // progressBar.text('0%');
    progressBar.attr('aria-valuenow', 0);
    progressBar.css('width', 0);

    progressBar.removeClass('d-none');
    progressBar.fadeIn();
    $('#text-progress-holder').css("visibility", "visible");

    $('#output').val('');

    $.ajax({
        // xhr: function () {
        //     var xhr = new window.XMLHttpRequest();
        //     xhr.upload.addEventListener("progress",  function (evt) {
        //         if (evt.lengthComputable) {
                    // var percentComplete = 0;
                    // percentComplete = (evt.loaded / evt.total) * 100;
                    // // Place progress bar visibility code here
                    //
                    // console.log("Upload:", percentComplete)
                    // // progressBar.text(percentComplete + '%');
                    // progressBar.attr('aria-valuenow', percentComplete);
                    // progressBar.css('width', percentComplete + '%');
                    //
                    //
                    // setTimeout(function () {
                    //     progressBar.fadeOut('fast', function () {
                    //         progressBar.addClass('d-none');
                    //         $('#text-progress-holder').css("visibility", "hidden");
                    //
                    //     });
                    // }, 0);



        //         }
        //     }, false);
        //     return xhr;
        // },
        method: "POST",
        url: "/deid-tool/textmod",
        contentType: false,
        data: data,
        cache: false,
        processData: false,
        timeout: 600000,
        async: false,
        success:  function (response) {
            // $('.deidentifybtn').prop('disabled', true);
            // console.log("response: " + response);

            // var usrfeedback = $($.parseHTML(response)).find("#feedbackForText");
            var usrfeedback = $('<div />').append(response).find('#feedbackForText').html();
            var ccdaXMLDeID = $('<div />').append(response).find('#ccdaDeID').html();
            console.info("usrfeedback: " + usrfeedback);
            // console.info("ccdaXMLDeID: " + ccdaXMLDeID);
            // console.log(usrFeedbackForText);
            // $('#outputText').show();
            // await timeout(500);
            $('#feedbackText').html(usrfeedback);
            $('#outputText').html(ccdaXMLDeID);
                // var outputText = $('#outputText').html($(response).find("#ccdaDeID").html()).fadeIn(100);
            // console.info("feedbackText: " + userFeedbackForText);
            console.info("outputText: " + outputText);
            // $('#outputText').html(response).fadeIn(100);
            setTimeout(function () {
                $("#feedbackAlert").fadeOut(3000);
            }, 1000);
            $("html, body").animate({ scrollTop: $("#removePII").offset().top }, 500);
            $('#loadinggif').css("visibility", "hidden");
            $('#loadinggif-holder').css("visibility", "hidden");


        },
        error: function (xhr, status) {
            if(xhr.status == 413){
                alert("Error occurred: XML too large!");
                return false;
            }

            alert("Error occurred! Please refresh the page and try again!");
            // var usrfeedback = $('<div />').append(response).find('#feedbackForText').html();
            // // var usrfeedback = $($.parseHTML(response)).filter("#feedback");
            // // $('#outputText').show();
            // // $('#outputText').html(response);
            // $('#usrFeedbackForText').html(usrfeedback);
        }
    });
}

function validateFn(xmlString) {
    let stack = [];
    const regex = /<([^>]+)>/g;
    let match;
    while ((match = regex
        .exec(xmlString)) !== null) {
        if (match[1]
            .charAt(0) === '/') {
            if (stack.length === 0
                ||
                stack.pop() !== match[1].slice(1)) {
                return false;
            }
        } else {
            stack.push(match[1]);
        }
    }
    return stack.length === 0;
}


function monitorTextInput(){
    $("#textinput").each(function() {
        var elem = $(this),
            oldValue;

        elem.on('focus', function () {
            elem.data('oldVal', elem.val());
            elem.data('oldLen', elem.data('oldVal').length);
        });

        // Look for changes in the value,
        // bind 'input' event to the textbox to fire the function
        // every time the input changes (paste, delete, type etc.)
        elem.bind("input", function(event){
            oldValue = elem.data('oldVal');
            // update oldVal
            elem.data('oldVal', elem.val());
            // check if pasted
            if (elem.val().length - elem.data('oldLen') > 500 ) {
                $('#textinput').scrollTop($('#textinput')[0].scrollHeight);
                $("html, body").animate({ scrollTop: $("#removePII").offset().top }, 500);
            }
            // update input value length
            //elem.data('oldLen', elem.data('oldVal').length);

            // update #table2
            //foo(oldValue, elem.val()) ;
        });
    });
}



