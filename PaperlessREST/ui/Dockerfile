FROM nginx
COPY PaperlessREST/ui /usr/share/nginx/html
COPY PaperlessREST/ui/cors.conf /etc/nginx/conf.d/cors.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
