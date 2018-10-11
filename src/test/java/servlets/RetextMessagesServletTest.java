package servlets;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static servlets.RetextMessagesServlet.CONFIRM_DELETE_JSP;

@RunWith(MockitoJUnitRunner.class)
public class RetextMessagesServletTest {

    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    public void doGet_forwardsUserToConfirmDelete_onDeletePath() throws Exception {
        // Arrange
        when(httpServletRequest.getPathInfo()).thenReturn("/delete");
        when(httpServletRequest.getRequestDispatcher(CONFIRM_DELETE_JSP)).thenReturn(requestDispatcher);
        String expectedId = "1";
        when(httpServletRequest.getParameter("id")).thenReturn(expectedId);

        // Act
        RetextMessagesServlet retextMessagesServlet = new RetextMessagesServlet();
        retextMessagesServlet.doGet(httpServletRequest, httpServletResponse);

        // Assert
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
    }

    @Test
    public void doGet_forwardsWithIdFromRequest_onDeletePath() throws Exception {
        // Arrange
        when(httpServletRequest.getPathInfo()).thenReturn("/delete");
        when(httpServletRequest.getRequestDispatcher(CONFIRM_DELETE_JSP)).thenReturn(requestDispatcher);
        int expectedId = 1249530215;
        when(httpServletRequest.getParameter("id")).thenReturn(String.valueOf(expectedId));

        // Act
        RetextMessagesServlet retextMessagesServlet = new RetextMessagesServlet();
        retextMessagesServlet.doGet(httpServletRequest, httpServletResponse);

        // Assert
        InOrder inOrder = Mockito.inOrder(requestDispatcher, httpServletRequest);
        inOrder.verify(httpServletRequest).setAttribute("id", expectedId);
        inOrder.verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
    }

    @Test
    public void doGet_throwsRuntimeExceptionOnDeletePath_WhenDispatcherThrowsServletException() throws Exception {
        // Arrange
        when(httpServletRequest.getPathInfo()).thenReturn("/delete");
        when(httpServletRequest.getRequestDispatcher(CONFIRM_DELETE_JSP)).thenReturn(requestDispatcher);
        String expectedId = "1";
        when(httpServletRequest.getParameter("id")).thenReturn(expectedId);
        ServletException expectedException = new ServletException();
        doThrow(expectedException).when(requestDispatcher).forward(httpServletRequest, httpServletResponse);

        // Act
        RetextMessagesServlet retextMessagesServlet = new RetextMessagesServlet();
        try {
            retextMessagesServlet.doGet(httpServletRequest, httpServletResponse);
            fail("Should have thrown RuntimeException.");
        } catch (RuntimeException e) {
            assertEquals(expectedException, e.getCause());
        }
    }

    @Test
    public void doGet_throwsRuntimeExceptionOnDeletePath_WhenDispatcherThrowsIOException() throws Exception {
        // Arrange
        when(httpServletRequest.getPathInfo()).thenReturn("/delete");
        when(httpServletRequest.getRequestDispatcher(CONFIRM_DELETE_JSP)).thenReturn(requestDispatcher);
        String expectedId = "1";
        when(httpServletRequest.getParameter("id")).thenReturn(expectedId);
        IOException expectedException = new IOException();
        doThrow(expectedException).when(requestDispatcher).forward(httpServletRequest, httpServletResponse);

        // Act
        RetextMessagesServlet retextMessagesServlet = new RetextMessagesServlet();
        try {
            retextMessagesServlet.doGet(httpServletRequest, httpServletResponse);
            fail("Should have thrown RuntimeException.");
        } catch (RuntimeException e) {
            assertEquals(expectedException, e.getCause());
        }
    }

}