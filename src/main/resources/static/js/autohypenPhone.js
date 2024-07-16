var autoHypenPhone = function(str){
      str = str.replace(/[^0-9]/g, '');
      var tmp = '';
      if( str.length < 4){
          return str;
      }else if(str.length < 7){
          tmp += str.substr(0, 3);
          tmp += '-';
          tmp += str.substr(3);
          return tmp;
      }else if(str.length < 11){
          tmp += str.substr(0, 3);
          tmp += '-';
          tmp += str.substr(3, 3);
          tmp += '-';
          tmp += str.substr(6);
          return tmp;
      }else{
          tmp += str.substr(0, 3);
          tmp += '-';
          tmp += str.substr(3, 4);
          tmp += '-';
          tmp += str.substr(7);
          return tmp;
      }

      return str;
}


var empPhone = document.getElementById('empPhone');
var memberPhone1 = document.getElementById('memberPhone1');
var memberPhone2 = document.getElementById('memberPhone2');
var userPhone = document.getElementById('userPhone');

empPhone.onkeyup = function(){
  console.log(this.value);
  this.value = autoHypenPhone( this.value ) ;
}

memberPhone1.onkeyup = function(){
  console.log(this.value);
  this.value = autoHypenPhone( this.value ) ;
}

memberPhone2.onkeyup = function(){
  console.log(this.value);
  this.value = autoHypenPhone( this.value ) ;
}

userPhone.onkeyup = function(){
  console.log(this.value);
  this.value = autoHypenPhone( this.value ) ;
}


