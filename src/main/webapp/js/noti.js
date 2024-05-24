document.addEventListener('DOMContentLoaded', function() {
  let successBtn = document.getElementById('success');
  let toastContainer = document.querySelector('.toasts');
  
  
  
   let successBtn1 = document.getElementById('error');
  	let toastContainer1 = document.querySelector('.toasts1');

  function createToast() {
    let toast = document.createElement('div');
    toast.classList.add('toast', 'fade', 'show');

    toast.innerHTML = `
      <div class="toast-header">
        <strong class="me-auto" style=" background-color: transparent;">Success</strong>
       
      </div>
      <div class="toast-body">
        Successful implementation!
      </div>
    `;

    toast.addEventListener('click', function(e) {
      if(e.target.closest('.btn-close')) {
        toast.remove(); 
      }
    });

    if(toastContainer) {
      toastContainer.appendChild(toast);

      setTimeout(() => {
        toast.remove();
      }, 20000);
    } else {
      console.error('Toast container not found');
    }
  }

  if(successBtn) {
    successBtn.addEventListener('click', createToast);
  } else {
    console.error('Success button not found'); 
  }
  
  
  
  
  function createToast1() {
    let toast1 = document.createElement('div');
    toast1.classList.add('toast1', 'fade', 'show');

    toast1.innerHTML = `
      <div class="toast-header">
        <strong class="me-auto" style=" background-color: transparent;">Cancel</strong>
       
      </div>
      <div class="toast-body">
        Cancel implementation!
      </div>
    `;

    toast1.addEventListener('click', function(e) {
      if(e.target.closest('.btn-close')) {
        toast1.remove(); 
      }
    });

    if(toastContainer1) {
      toastContainer1.appendChild(toast1);

      setTimeout(() => {
        toast1.remove();
      }, 20000);
    } else {
      console.error('Toast container not found');
    }
  }

  if(successBtn1) {
    successBtn1.addEventListener('click', createToast1);
  } 
});