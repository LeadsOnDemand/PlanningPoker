module.exports = {
  entry: './public/app.jsx',
  output: {
    path: __dirname+'/public',
    filename: 'bundle.js'
  },
  resolve: {
      root:__dirname,
      alias:{
         
         Table:'public/components/Table.jsx',
         Player:'public/components/Player.jsx' ,
         Cards:'public/components/Cards.jsx',
        
         Storecards:'public/components/Storecards.jsx',
          Leave:'public/components/Leave.jsx',
       Count:'public/components/Count'
      },
    extensions: ['', '.js', '.jsx']
  },
  module: {
    loaders: [
      {
        loader: 'babel-loader',
        query: {
          presets: ['react', 'es2015']
        },
        test: /\.jsx?$/,
        exclude: /(node_modules|bower_components)/
      }
    ]
  }
};
