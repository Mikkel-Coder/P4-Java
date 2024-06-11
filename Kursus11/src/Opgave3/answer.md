> __QUESTION__ Some of the above faults and errors you are probably already catching. Consider by design, what to do when entering those fault conditions. Anything you can change from standard output to actual
sensible behavior beside stopping some functionality?

> __ANSWER__ Yes as from the server. When an IOException error ocures, we should try to close the client socket instead of terminating by fource in TCP. Also from the client side, we should try to close the socket if anything form the train protocol went wrong. 