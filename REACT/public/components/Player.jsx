
var React=require('react')
var Player = React.createClass({
    
addPlayer:function(e){
 
 e.preventDefault()   
var player=this.refs.name.value 
this.refs.name.value=""
if(player==""){
 
 alert("Please enter a valid name")
}
else{
 this.props.onSubmit(player)
 
}

    
    
}   , 
    
    
 render: function() {
   return (
      <div>
      <form onSubmit={this.addPlayer}>
      <div className="form-group">
      <label>Enter Your Name </label>
      <input className="form-control" type="text" ref="name" />
      </div>
      <button className="btn btn-primary"> Join Table </button> 
      
      </form>
      </div>
    );
 }
});
module.exports=Player
