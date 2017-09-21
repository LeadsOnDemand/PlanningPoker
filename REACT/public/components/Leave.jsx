var React=require('react')



var Leave=React.createClass({
    
    
    
    
    
render:function(){
    
   var removeplayer= this.props.currentplayer
   var allplayers= this.props.allplayers
    var that=this
    var leave=function(){
 
 
  
  return <button onClick={function(){
  var filteredplayers= allplayers.map(function(current){
    
    if(current==removeplayer)
    return "player left"
    
    else
    return current
    
    // return current!=removeplayer
  })
  
 that.props.onLeave(filteredplayers,removeplayer)
  
  
  
 }}>Leave</button>
 
  
 }

    
    return(
        <div>
        
       
     {leave()}
  
     

        
       
        
        </div>
        
        )
    
    
    
    
    
}    
    
    
    
})
module.exports=Leave