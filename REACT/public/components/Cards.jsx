
var React=require('react')
var axios=require("axios")
var Table=require("Table")

var Cards = React.createClass({
    
getInitialState:function(){
    
 return{
     
   cards:[]    
     
 }   
  
    
    
},
       
    
    
componentDidMount: function(){

var that=this
axios.get('https://deckofcardsapi.com/api/deck/fjt3ifclrhos/draw/?count=2').then(function(res){
    
that.setState({

cards:[...res.data.cards]    
    
})
    
})



    
    
    
}
 ,  
    
    
 render: function() {
     var cards=this.state.cards.map(function(current){
         
         
    return <img src={current.image} width="50" height="50"/>     
         
     })
   return (
      <div>
      {cards}
     
      
      
      </div>
    );
 }
});
module.exports=Cards
