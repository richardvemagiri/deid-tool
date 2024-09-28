$(document).ready(function () {

    // $('.category-checkbox').on('click', function() {
    //     $('.category-checkbox').each(function() {
    //         if ($(this).prop("checked"))
    //             $("this").prop("checked", false);
    //         else
    //             $(this).prop("checked", true);
    //         })
    // });

    // showFileUploadPage();
    //show default tab
    showTextPage();
    // showFileForUserProfile();
    $('#testDiv').hide();
    // $('#deidentifybtn').fadeOut();
    $('#disclaimer').fadeIn();
    $('.category-checkbox').prop("checked", true);


    // check for browser support
    if (!(window.File && window.FileReader && window.FileList && window.Blob)) {
        $('#browserSupportWarn').addClass("alert-danger");
        $('#browserSupportWarn').html("<p style=\"color: red; text-align: center;\">Please not that certain features of the application may not be " +
            "fully supported due to browser limitations. Please upgrade your browser.</p>");
    }

    var myFileUploadModal = new bootstrap.Modal(document.getElementById('fileUploadModal'), {
        keyboard: false
    });

    // $('#textmodepage').on('click', '#removePII', function(){
    //
    //
    //
    //     $("html, body").animate({ scrollTop: $("#header").offset().top }, 100);
    //     scrollToButton();
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

    // $('#fileuploadpage').on('click', '#deidentifybtn', function(){
    //     // var html = null;
    //     // $.get("model-SelectFile.html",
    //     //     {'_':$.now()}
    //     // ).done(function(data){
    //     //     html = data;
    //     // }).fail(function(jqXHR, testStatus){
    //     //     alert("Error occurred");
    //     // });
    //     var myFileUploadModal = new bootstrap.Modal(document.getElementById('fileUploadModal'), {
    //         keyboard: false
    //     });
    //
    //     // check if file is picked
    //     if ($('#fileUpload').val().length === 0) {
    //         // $('#exampleModal1').load("modalFeedBack");
    //
    //         // var modalfeedBack = $($.parseHTML(html)).filter("#exampleModal");
    //         $('#fileUploadModalContent').html("File Upload: Please select a file!");
    //         myFileUploadModal.show();
    //         // myModal.html(modalfeedBack);
    //         // alert("Please select a file");
    //         return false;
    //     }
    //
    //     // check if file is XML
    //     var allowedExtensions = /(\.xml|\.XML)$/i;
    //     if (!allowedExtensions.exec($('#fileUpload').val())) {
    //         $('#fileUploadModalContent').html("File upload: Please select a valid C-CDA XML file!");
    //         myFileUploadModal.show();
    //         // alert('Please select a valid C-CDA XML file');
    //         return false;
    //     }
    //
    //     // check if file is too big
    //     var maxFileSize = 5242880;
    //     if ($('#fileUpload')[0].files[0].size > maxFileSize) {
    //         alert("File is larger than " + Math.round(maxFileSize / 1000000) + " MB");
    //         return false;
    //     }
    //
    //     $('#deidentifybtn').removeAttr("data-bs-target");
    //     event.preventDefault();
    //     submitFile();
    //
    //
    // });



});


    $('#fileploadForm').submit(function (event) {

        var myFileUploadModal = new bootstrap.Modal(document.getElementById('fileUploadModal'), {
            keyboard: false
        });


        // check if file is picked
        if ($('#fileUpload').val().length === 0) {
            // $('#exampleModal1').load("modalFeedBack");

            // var modalfeedBack = $($.parseHTML(html)).filter("#exampleModal");
            $('#fileUploadModalContent').html("Please select a file!");
            myFileUploadModal.show();
            // myModal.html(modalfeedBack);
            return false;
        }

        // check if file is XML
        var allowedExtensions = /(\.xml|\.XML)$/i;
        if (!allowedExtensions.exec($('#fileUpload').val())) {
            $('#fileUploadModalContent').html("Please select a valid C-CDA XML file!");
            myFileUploadModal.show();
            // alert('Please select a valid C-CDA XML file');
            return false;
        }

        // check if file is too big
        var maxFileSize = 5242880;
        if ($('#fileUpload')[0].files[0].size > maxFileSize) {
            alert("File is larger than " + Math.round(maxFileSize / 1000000) + " MB");
            return false;
        }

        // check if checkboxes are selected
        let identifierTypes = [];
        $(".category-checkbox:checked").each(function() {
            identifierTypes.push($(this).val());
        });
        if(identifierTypes.length<=0){
            alert("No identifier categories selected!");
            return false;
        }
        // $(".category-checkbox").is(':checked').each(function() {
        //     alert(this.value);
            // arr.push($(this).val());
        // });
        // alert(arr1.length);
        // if(arr1.length<=0){
        //     alert("No category checkboxes picked!");
        //     return false;
        // }



        $('#deidentifybtn').removeAttr("data-bs-target");
        event.preventDefault();
        submitFile();


    });


function timeout(ms){
    console.log("Inside timeout ...");
    return new Promise(res => setTimeout(res, ms));
}

function showTextPage(){

    $('#texttab').attr('aria-current', 'true');
    $('#texttab').addClass('active fw-bolder');
    $('#fileuploadtab').removeAttr('aria-current');
    $('#fileuploadtab').removeClass('active fw-bolder');
    $('#textmodepage').show();
    $('#fileuploadpage').hide();
    $('#text-progress-holder').css("visibility", "hidden");

    // $('#fileUploadContainer').addClass('d-none');
    // $('#fileUploadContainer').css("visibility", "hidden");

    // $.ajax({
    //     method: "GET",
    //     url: "/deid-tool/textmode",
    //     contentType: false,
    //     cache: false,
    //     processData: false,
    //     timeout: 60000,
    //     success: async function (response) {
    //         // alert("ajax success");
    //         // $('.deidentifybtn').prop('disabled', true);
    //         var pgCtnt = $($.parseHTML(response)).filter("#textModContainer");
    //         $('#textmodepage').html(pgCtnt);
    //
    //     },
    //     error: function (response) {
    //         alert('Error occurred! Please refresh the page and try again!');
    //         $('#modalContent').html("Error occurred! Please refresh the page and try again.");
    //         var usrfeedback = $($.parseHTML(response)).filter("#feedback");
    //         $('#testDiv').show();
    //         $('#testDiv').html(usrfeedback);
    //         $('#download').html(response);
    //     }
    // });

}

function showFileUploadPage(){
    // alert("FileUploadPage");
    $('#fileuploadtab').attr('aria-current', 'true');
    $('#fileuploadtab').addClass('active fw-bolder');
    $('#texttab').removeAttr('aria-current');
    $('#texttab').removeClass('active fw-bolder');
    // $('#fileUploadContainer').addClass('d-block');
    $('#textmodepage').hide();
    $('#fileuploadpage').show();
    // $('#fileUploadContainer').css("visibility", "visible");
    // $('#textModContainer').css("visibility", "hidden");



    // $.ajax({
    //     method: "GET",
    //     url: "/deid-tool/fileUpload",
    //     contentType: false,
    //     cache: false,
    //     processData: false,
    //     timeout: 60000,
    //     success: async function (response) {
    //         // alert("ajax success");
    //         // $('.deidentifybtn').prop('disabled', true);
    //         var pgContnt = $($.parseHTML(response)).filter("#fileUploadContainer");
    //         $('#fileuploadpage').html(pgContnt);
    //
    //     },
    //     error: function (response) {
    //         alert('Error occurred! Please refresh the page and try again!');
    //         $('#modalContent').html("Error occurred! Please refresh the page and try again.");
    //         var usrfeedback = $($.parseHTML(response)).filter("#feedback");
    //         $('#testDiv').show();
    //         $('#testDiv').html(usrfeedback);
    //         $('#download').html(response);
    //     }
    // });

}

function submitFile() {
    console.log("File submitted for processing...");

    var data = new FormData();
    data.append('file', $('#fileUpload')[0].files[0]);

    let arr = [];
    $(".category-checkbox:checked").each(function() {
        arr.push($(this).val());
    });

    // alert($('input[name="category"]:checked').val());
    data.append('categories', arr);

    var progressBar = $('#progress_bar');
    // progressBar.text('0%');
    progressBar.attr('aria-valuenow', 0);
    progressBar.css('width', 0);

    progressBar.removeClass('d-none');
    progressBar.fadeIn();
    $('#progress-holder').css("visibility", "visible");

    $('#download').children().remove();

    $.ajax({
        xhr: function () {
            var xhr = new window.XMLHttpRequest();
            xhr.upload.addEventListener("progress", async function (evt) {
                if (evt.lengthComputable) {
                    var percentComplete = 0;
                    percentComplete = (evt.loaded / evt.total) * 100;
                    // Place progress bar visibility code here

                    console.log("Upload:", percentComplete)
                    // progressBar.text(percentComplete + '%');
                    // progressBar.attr('aria-valuenow', percentComplete);
                    // progressBar.css('width', percentComplete + '%');
                    progressBar.val(percentComplete);

                    setTimeout(function () {
                        progressBar.fadeOut('fast', function () {
                            progressBar.addClass('d-none');
                            $('#progress-holder').css("visibility", "hidden");

                        });
                    }, 0);

                }
            }, false);
            return xhr;
        },
        method: "POST",
        url: "/deid-tool/",
        contentType: false,
        data: data,
        cache: false,
        processData: false,
        timeout: 60000,
        success: async function (response) {
            // $('.deidentifybtn').prop('disabled', true);
            var usrfeedback = $($.parseHTML(response)).filter("#feedback");
            console.log(usrfeedback);
            $('#testDiv').show();
            $('#testDiv').html(usrfeedback);
            console.log("Before await timeout");
            await timeout(500);
            console.log("After await timeout");
            $('#download').html(response).fadeIn(100);
            setTimeout(function () {
                $("#feedback").fadeOut(3000);
            }, 1000);
        },
        error: function (response) {
            // alert('Error occurred! Please refresh the page and try again!');
            $('#fileUploadModalContent').html("Error occurred! Please refresh the page and try again.");
            var usrfeedback = $($.parseHTML(response)).filter("#feedback");
            $('#testDiv').show();
            $('#testDiv').html(usrfeedback);
            $('#download').html(response);
        }
    });
}

function submitText() {



    console.log("File submitted for processing...");
    var data = new FormData();
    data.append('ccdaXML', $('#textinput').val());


    $.ajax({
        method: "POST",
        url: "/deid-tool/textmode",
        contentType: false,
        data: data,
        cache: false,
        processData: false,
        timeout: 600000,
        success: async function (response) {
            // $('.deidentifybtn').prop('disabled', true);
            var usrfeedback = $($.parseHTML(response)).filter("#userFeedbackForText");
            var ccdaXMLDeID = $($.parseHTML(response)).filter("#ccdaDeID");
            console.log(ccdaXMLDeID);
            // console.log(usrFeedbackForText);
            // $('#outputText').show();
            $('#outputText').html(response);
            $("html, body").animate({ scrollTop: $("#removePII").offset().top }, 500);

        },
        error: function (response) {
            alert('Error occurred! Please try again!');
            // var usrfeedback = $($.parseHTML(response)).filter("#feedback");
            // $('#outputText').show();
            $('#outputText').html(response);
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


function scrollToButton(){
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

function showFileForUserProfile() {

    $.ajax({
        method: "GET",
        url: "/deid-tool/loadFileForUser",
        contentType: false,
        cache: false,
        processData: false,
        timeout: 600000,
        success: async function (response) {
            // $('.deidentifybtn').prop('disabled', true);
            var usrfeedback = $($.parseHTML(response)).filter("#feedback");
            console.log(usrfeedback);
            $('#testDiv').show();
            $('#testDiv').html(usrfeedback);
            console.log("Before await timeout");
            await timeout(500);
            console.log("After await timeout");
            $('#download').html(response).fadeIn(100);
            setTimeout(function () {
                $("#feedback").fadeOut(3000);
            }, 1000);

        },
        error: function (response) {
            alert('Error occurred! Please try again!');
            // var usrfeedback = $($.parseHTML(response)).filter("#feedback");
            // $('#outputText').show();
            $('#outputText').html(response);
        }
    });
}
