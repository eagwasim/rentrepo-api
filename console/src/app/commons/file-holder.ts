import {NgxFirebaseClientService} from "@ngx-firebase/client";

export class FileHolder {
  processing = false;
  percentageUpload = 0;
  mode = 'indeterminate';
  placeholder = "/assets/images/placeholder-image.png";

  constructor(private firebaseClientService: NgxFirebaseClientService, private selectedFile: string) {
  }

  isEmpty(): boolean {
    return this.selectedFile == null;
  }

  remove() {
    this.mode = 'indeterminate';
    this.processing = true;
    let self = this;
    let fileReference = this.firebaseClientService.storage().refFromURL(this.selectedFile);
    fileReference.delete().then(function () {
      self.selectedFile = null;
      self.processing = false;
    }).catch(function (error) {
      self.processing = false;
      console.log(error);
    });
  }

  onfileSelected(fileInput) {
    if (!fileInput.target.files || !fileInput.target.files[0]) {
      return;
    }
    let self = this;

    let file = fileInput.target.files[0];
    let storage = this.firebaseClientService.storage().ref();
    let fileRef = storage.child('images/' + new Date().getMilliseconds() + "" + file.name);

    this.mode = 'determinate';
    this.processing = true;
    self.percentageUpload = 0;

    let uploadTask = fileRef.put(file);

    uploadTask.on('state_changed', function (snapshot) {
      // Observe state change events such as progress, pause, and resume
      // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
      self.percentageUpload = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
    }, function (error) {
      self.processing = false;
      self.percentageUpload = 0;
    }, function () {
      self.processing = false;
      self.percentageUpload = 0;
      // Handle successful uploads on complete
      // For instance, get the download URL: https://firebasestorage.googleapis.com/...
      uploadTask.snapshot.ref.getDownloadURL().then(function (downloadURL) {
        self.selectedFile = downloadURL;
      });
    });
  }

  getFile() {
    if (this.isEmpty()) {
      return this.placeholder;
    }
    return this.selectedFile;
  }

  openFile(index) {
    document.getElementById('image-' + index).click();
  }
}
