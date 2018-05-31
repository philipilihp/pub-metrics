package de.philipilihp.pub.metrics;


import com.codahale.metrics.servlets.AdminServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/metrics/*"})
public class PubMetricsServlet extends AdminServlet {
}
