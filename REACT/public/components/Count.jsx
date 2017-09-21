var React=require('react')



var Count=React.createClass({
    
getInitialState:function(){
    return{
        
        count:1,
        clicked:false,
        clickedcount:0
        
        
    }
    
    
    
} ,   



// componentWillReceiveProps:function(newProps){
//     this.setState({
        
        
//         clicked:this.state.clicked
       
//     })
    
    
// },
    




    
    
    
render:function(){
    
 var current=this.props.currentplayer
 var allplayers=this.props.allplayers
 var count=this.props.count
 var that=this
 
 var clickedstatus=function(){
 
 if(!that.state.clicked)    {
    return(
        <div>
        <button onClick={function(){
    //  document.querySelector('button').style.visibility = 'hidden';
    
      that.props.onFold(current)    
      
     
    
         that.setState({
             
             
             clicked:true
         })
        
       
   
    
       
        
        
    }}>Fold</button>
    
    <button onClick={function(){
    
     that.props.onCheck(current)    
     
      that.setState({
             
             
             clicked:true
         })
    
    
    
   
    
 }}> Check </button>
 
 
   
        
        </div>
        
        
        
        ) 
     
 }
     else{
         
         
    return <p>{current} has played</p>     
         
     }
     
     
 }
  

    return(
        <div>
        
       
     {clickedstatus()}
    
     

        
       
        
        </div>
        
        )
    
    
    
    
    
}    
    
    
    
})
module.exports=Count