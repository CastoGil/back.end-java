package filters;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/*"})
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        var origenesPermitidos = List.of("http://localhost:5500", "http://127.0.0.1:5500", "https://castogil.github.io"  );
        String origin = ((HttpServletRequest) request).getHeader("origin");

        if (origenesPermitidos.contains(origin)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.addHeader("Access-Control-Allow-Origin", origin);
            httpResponse.addHeader("Access-Control-Allow-Headers", "*");
            httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");

            // Manejar solicitudes OPTIONS
            if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
                httpResponse.setStatus(HttpServletResponse.SC_OK);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
