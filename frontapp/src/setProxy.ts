import { createProxyMiddleware } from 'http-proxy-middleware';

module.exports = function(app: any) {
    app.use(
        '/api', // 프록시할 엔드포인트의 경로
        createProxyMiddleware({
            target: 'http://localhost:8045', // 백엔드 서버의 주소
            changeOrigin: true,
            pathRewrite: { '^/api': '' }, // '/api' 경로를 제거 (필요에 따라 조정)
        })
    );
};
