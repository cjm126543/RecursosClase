import numpy as np

def check_rank(A, E):
    extended_rank = np.linalg.matrix_rank(E)
    if (np.linalg.matrix_rank(A) != extended_rank):
        raise ValueError("La matriz introducida es incompatible")
    if (extended_rank < A.shape[1]):
        raise ValueError("La matriz introducida es compatible indeterminado")

def GaussSeidel(M, x_0):
    # Comprueba que sea compatible determinado
    size = M.shape[1] - 1
    try:
        check_rank(M[:,range(size)], M)
    except ValueError as exception:
        print(exception)
        return None
    
    # Declaramos los criterios de parada
    max_iters = 1000
    tol = 1e-9

    # Variables necesarias para la ejecucion
    op1 = 0
    op2 = 0
    coef_mat = M[:, range(size)]
    ind_matrix = M[:, size]
    sol = x_0
 
    # Comienza el algoritmo
    for num_iter in range(max_iters):
        for i in range(size):
            op1 = np.dot(coef_mat[i, :i], sol[:i])
            op2 = np.dot(coef_mat[i, i + 1:], sol[i + 1:])
            sol[i] = (ind_matrix[i] - op1 - op2) / coef_mat[i, i]
        r = np.linalg.norm(np.dot(coef_mat, sol) - ind_matrix)
        print(f'{r} menor que {tol}?')
        if r == True:
            print(sol)
            return sol

    print("Gauss-Seidel termina por maximo de iteraciones")
    return None


eq_coef = np.array([[1, 1, 1],
                    [100, 500, 250], 
                    [5, 10, 20]], dtype=float)
eq_ind = np.array([[50], [17550], [525]], dtype=float)
eq_complete = np.hstack((eq_coef, eq_ind))
x_1 = np.array([[10], [30], [10]], dtype=float)
sol_gs_1 = GaussSeidel(eq_complete, x_1.copy())
print(f'Solucion {x_1}: gauss-seidel: {sol_gs_1}')