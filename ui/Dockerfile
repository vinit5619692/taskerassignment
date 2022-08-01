FROM nginx:alpine AS base-nginx-container

# Remove the default nginx config
RUN rm -v /etc/nginx/nginx.conf

# Remove the default nginx config
RUN rm -v /usr/share/nginx/html/index.html

# Update folder permissions, especially important if you're running inside RedHat OpenShit
RUN chmod -R a+rwx /usr/share

# Expose specific port
EXPOSE 8080

# Copy my nginx Config
COPY nginx.conf /etc/nginx/

# Copy the Source Code
COPY ./web /usr/share/nginx/html

# Start nginx service
CMD ["nginx","-g","daemon off;"]