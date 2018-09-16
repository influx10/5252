$(document).ready(function() {
    $("#insurance").load("insurance.html", function() {
        $('#cb1').on('change', function() {
            $('#firstRow').removeClass('hide');
        });

        function populateOptions(url, ele) {
            $.ajax({
                type: 'get',
                url: url,
                dataType: 'json',
                success: function(data) {
                    var options = '';
                    for(var i=0; i<data.data.length; i++) {
                        options += '<option value="' + data.data[i] + '">' + data.data[i] + '</option>';
                    }
                    $("#"+ele).append(options);
                },
                error: function() {
                    console.log('error');
                }
            });
        }

        populateOptions('/CarInsurance_Responsive/apis/carType.json', 'carType');
        populateOptions('/CarInsurance_Responsive/apis/fuelType.json', 'fuelType');
        populateOptions('/CarInsurance_Responsive/apis/registrationState.json', 'registrationState');

        function validateForm(form) {
            var counter = 0;
            form.find('.form-control').each(function() {
                $this = $(this);
                if($this.val()) {
                    if($this.hasClass('uname')) {
                        if($this.val().length >= 2) {
                            $this.parent().removeClass('has-error');
                        } else {
                            $this.parent().addClass('has-error');
                            counter++;
                        }
                    } else if($this.hasClass('pnumber')) {
                        if($this.val().length == 10) {
                            $this.parent().removeClass('has-error');
                        } else {
                            $this.parent().addClass('has-error');
                            counter++;
                        }
                    } else {    
                        $this.parent().removeClass('has-error');
                    }
                } else {
                    $this.parent().addClass('has-error');
                    counter++;
                }
            });
            if(counter > 0) {
                return false
            } else {
                return true
            }
        }

        $('#myForm').on('submit',function(e){
            e.preventDefault();
            var isValid = validateForm($(this));
            if(isValid) {
                onSuccess();
            }
        });

        function onSuccess() {
            $('#firstRow').addClass('hide');
            $.ajax({
                type: 'post',
                url: '/CarInsurance_Responsive/apis/getQ.json',
                dataType: 'json',
                success: function(data) {
                    var options = '';
                    for(var i=0; i<data.bank.length; i++) {
                        options += '<li id="ins'+ i +'" data-name="' + data.bank[i].name  + '" data-price="' + data.bank[i].price  + '" draggable="true" ondragstart="drag(event)">' + data.bank[i].name  + '<br />' + data.bank[i].price  + '</li>';
                    }
                    $("#banking").html(options);
                },
                error: function() {
                    console.log('error');
                }
            });
            $('#secondRow').removeClass('hide');
        }

        $('#buyInsurance').on('click', function(){
            $selectInsList = $('#bankingTarget').find('li');
            if($selectInsList.length > 0) {
                $('#bankingTarget').parent().removeClass('error');
                var finalJson = {};
                var insuranceDetails = {};
                finalJson['carType'] = $('#carType').val();
                finalJson['fuelType'] = $('#fuelType').val();
                finalJson['registrationState'] = $('#registrationState').val();
                finalJson['userName'] = $('#userName').val();
                finalJson['phoneNumber'] = $('#phoneNumber').val();
                insuranceDetails['name'] = $selectInsList.attr('data-name');
                insuranceDetails['price'] = $selectInsList.attr('data-price');
                finalJson['plan'] = insuranceDetails;
                $.ajax({
                    type: 'POST',
                    url: '/CarInsurance_Responsive/apis/getQ.json',
                    data: finalJson,
                    success: function(data) {
                        data.bank.policyNumber = '123456789'; // It will come from actual responce as data.data.policyNumber
                        $('#policyNumber').text(data.bank.policyNumber);
                        $("#myModal").modal();
                    },
                    error: function() {
                        console.log('error');
                    }
                });                
            } else {
                $('#bankingTarget').parent().addClass('error');
            }
            
        });
    });
    
});

function drag(e) {
    e.dataTransfer.setData("text", e.target.id);    
}

function allowdrop(e) {
    e.preventDefault();
}

function drop(e) {
    e.preventDefault();
    var data = e.dataTransfer.getData('text');
    var tgtElement = e.target;    
    if(e.target.tagName && e.target.tagName == 'LI') {
        tgtElement = e.target.parentElement;      
    }
    if($(tgtElement).attr('id') === 'bankingTarget' && $("#bankingTarget").find('li').length > 0) {
        alert('You cannot select more than one insurance');
    } else {
        tgtElement.appendChild(document.getElementById(data));        
    }    
}