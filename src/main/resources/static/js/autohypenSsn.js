var autoHypenSsn = function(str){
      str = str.replace(/[^0-9]/g, '');
      var tmp = '';
      if( str.length < 7){
          return str;
      }else if(str.length <14){
          tmp += str.substr(0, 6);
          tmp += '-';
          tmp += str.substr(6);
          return tmp;
      }else{
          tmp += str.substr(0.6);
          tmp += '-';
          tmp += str.substr(6,7);
          return tmp;
      }

}

var empssn = document.getElementById('empssn');
empssn.onkeyup = function(){
  console.log(this.value);
  this.value = autoHypenSsn( this.value ) ;
}
