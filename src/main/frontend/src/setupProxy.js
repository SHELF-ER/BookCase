const { createProxyMiddleware } = require('http-proxy-middleware')

module.exports = (app) => {
  app.use(
    createProxyMiddleware(
      ['/api', '/none'],
      {
        target: 'http://localhost:8080',
        changeOrigin: true,
        router: {
          '/none': process.env.REACT_APP_V2_URL
        },
        pathRewrite: {
          '^/none': ''
        }
      }
    )
  );
}