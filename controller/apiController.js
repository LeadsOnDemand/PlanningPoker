var mongoose = require('mongoose');
var db = require('../model/dbschema');
var body= require('body-parser');


module.exports = function(app){

    app.use(body.json());
    app.use(body.urlencoded({extended:true}));
    app.use(function(req, res, next) {
        res.header("Access-Control-Allow-Origin", "*");
        res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        res.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
        next();
        });

    
    app.get('/activegames' , function(req,res){
        db.find(function(err, any)
        {
            if(err) throw err;
            res.json(any);
        });

    });

    app.get('/mygame/:id' , function(req,res){
        db.findById(mongoose.Types.ObjectId(req.params.id),
        function(err, anyName){
            if(err) throw err;
            res.json(anyName.players);
        });

    });



    app.post('/master', function(req,res)
        {
            
            console.log("Entered Post Method 1");
            if(req.body.id)
                {
                    console.log("entering if block");
                    db.findByIdAndUpdate(mongoose.Types.ObjectId(req.body.id),
                    {
                        name:req.body.name,                      
                    },
                    function(err,any)
                    {
                        if(err) throw err;
                        res.json(any);
                    });
                }
            
            else
                {
                    console.log("entering else block");
                   
                    var newGame = db();
                    newGame.name= req.body.name;
                    newGame.save(function(err,any)
                     {
                        if(err) throw err;
                        res.json(any);
                     });
                }

                console.log("exiting post method 1");
        });
        
     


        app.put('/join/:id/:yname/:i', function(req,res)
        {
            console.log("Entered Put Method 1");
          
            db.findOne({_id:req.params.id},
                    function(err,anyName)
                    {
                        if(err) throw err;
                    
                            var p = {
                                name:req.params.yname,
                                selectednum:req.params.i
                                }
                        anyName.players.push(p);
                        if(anyName.players.length<2)
                            {
                                console.log(" less Than 2 players");
                                anyName.save(function(err,xxx){
                                    if(err) throw err;
                                    res.send(xxx)
                                    });
                            }
                            
                           else
                            { 
                               
                            anyName.save(function(err,xxx){
                            if(err) throw err;
                            res.send(xxx)
                            });
                            console.log("More than two player")
                             }

                    });
        
          });


/*
          app.post('/join/:id/:i/:k', function(req,res)
          {
            
              db.findOne({_id:req.params.id, players:{_id:mongoose.Types.ObjectId(req.body.i)}},
                function(err,anyName)
                {
                    if(err) throw err;
                
                        var p = {
                            selectednum:req.params.k
                            }
                            console.log(p);
                    anyName.players.push(p);                        
                        
                        anyName.save(function(err,xxx){
                        if(err) throw err;
                        res.send(xxx)
                        });
                        
          
            });
        });
  



    app.delete('/activegames/:id' , function(req,res)

            {
                db.findByIdAndRemove(mongoose.Types.ObjectId(req.params.id),
                function(err,any)
                {
                    if(err)throw err;
                    res.json(any);
                });
            });

            app.delete('/mygame/:id/:d' , function(req,res)
            
                        {
                            db.find({_id:req.params.id, players:{_id:mongoose.Types.ObjectId(req.params.d)}},
                           
                           function(err,any) 
                           {
                               if(err) throw err;
                                   var x = req.params.d;                                  
                                   any.players._id(x).remove();
                                   any.save();
                                  
                                  
                                   /* any.players.findByIdAndRemove(x,function(err,any1){
                                       if(err)throw err;
                                       res.json(any);
                                   });
                                     
                                    
                                   /* anyName.save(function(err,xxx){
                                    if(err) throw err;
                                    res.send(xxx)
                                    });*/
      /*                  });
              });
              */
}


