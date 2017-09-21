
var React=require("react")
var Player=require('Player')
var axios=require('axios')
var Cards=require("Cards")
var Leave=require("Leave")

var Count=require("Count")

var Table = React.createClass({
 

 
 getInitialState:function(){
  
  return{
   totalplayers:[],
   count:0,
   player:0,
   playersyettoplay:0,
   playerchoice:[],
   left:0
   
   
   
  }
  
 },
 
 componentDidMount:function(){
  
  axios.get('https://deckofcardsapi.com/api/deck/fjt3ifclrhos/shuffle/').then(function(res){
   
   
   
   
   
  })
  
  
 },

 
 handleFold:function(currentplayer){
  
  // alert(this.state.playersyettoplay)
  
        this.setState({
   totalplayers:this.state.totalplayers,
   count:this.state.count,
   players:this.state.players,
   playersyettoplay:this.state.playersyettoplay-1,
   playerchoice:[...this.state.playerchoice,{name:currentplayer,choice:"fold"}]
   
  })

  
  
  
  
  
 },
 
 
 
handleCheck:function(currentplayer){
 
 // alert(this.state.playersyettoplay)
 
 this.setState({
   totalplayers:this.state.totalplayers,
   count:this.state.count,
   players:this.state.players,
   playersyettoplay:this.state.playersyettoplay-1,
   playerchoice:[...this.state.playerchoice,{name:currentplayer,choice:"checked"}]
   
  })
 
 
 
},


 
 handleSubmit:function(playername){
  
 
  
 if(this.state.count==1){
  this.setState({
   totalplayers:[...this.state.totalplayers,playername],
   count:this.state.count+1,
   players:this.state.players+1,
   playersyettoplay:this.state.playersyettoplay+1,
   playerchoice:[]
   
   
  })
  
  
 } 
 
 else{
  
  this.setState({
   totalplayers:[...this.state.totalplayers,playername],
   count:this.state.count+1,
   players:this.state.players+1,
   playersyettoplay:this.state.playersyettoplay+1
   
   
   
  })
  
  
 }
  
  
  
  
 },
 
 leaveTable:function(filteredplayers,removeplayer){
  
  var filteredchoice=this.state.playerchoice.filter(function(current){
   
   return current.name!=removeplayer
   
   
  })
  
  this.setState({
   
   totalplayers:filteredplayers,
   playersyettoplay:this.state.playersyettoplay,
   playerchoice:[...filteredchoice],
   count:this.state.count-1,
   left:this.state.left+1
   
  })
  
  
  
 },
  
  
  
  

 
 render: function() {
 
  var allplayers=this.state.totalplayers
  var count=this.state.count
  var that=this
  
  

  var opencards=function(){
   // alert(that.state.playersyettoplay)
   if(that.state.playersyettoplay<=0 ){
    
    
    
   
   
   return that.state.playerchoice.map(function(current){
  
  
  return (
   <div className="list-group-item">
   {current.name}:{current.choice}
   
   </div>
   
   )
  
  
 })
   
   
   
   }
   
  }
  
 
 
  
  var countplayers=function(){
   if(count>=2 && that.state.playersyettoplay>0 ){
       
      
      return <p className="alert alert-success">  game has started </p>
      }
      
       
       else if(count<=1){
        
       
       
       return <p className="alert alert-danger">Waiting for players </p>
        
       }
       
  
  
  
 }
 
 
 
 

  
  
  var totalplayers=function(){
   var count=that.state.count
   if(that.state.count>=2  ){
    
   return that.state.totalplayers.map(function(current){
    
   if(current!="player left"){
    return (
    <div>
       <div className="list-group-item">
       {current} 
       <Cards/>
      
       </div>
 
     
      <div>
      
       
  <Count  onFold={that.handleFold} onCheck={that.handleCheck} currentplayer={current} allplayers={allplayers} count={count} />
     
  <Leave onLeave={that.leaveTable} currentplayer={current} allplayers={allplayers}/>
   
      
      
       </div><br/>
   </div>
    )
   }
   
   
   
  })}
  
  
  
  
  else {
   
    return that.state.totalplayers.map(function(current){
   if(current!="player left"){
   return (
    <div>
       <div className="list-group-item">
       {current} has joined
       
       </div>
       
     
     
      </div>
      
      
      
      )}
    
    
      
  
  })}
  }
  
 
    
    
  
   
   
   
  
   
   
   
  
  
   return (
      <div className="container">
      <div className="jumbotron">
      
      <h1 className="display-3"> Poker Planning</h1>
      
      
      </div>
      <Player onSubmit={this.handleSubmit}/><br/>
     
      {totalplayers()}<br/>
      {countplayers()}
      {opencards()}
     
      
     
      
       
        
       
      </div>
    );
  }
});
module.exports=Table



