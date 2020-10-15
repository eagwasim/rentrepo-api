import {NgxFirebaseClientService} from "@ngx-firebase/client";
import heic2any from "heic2any";

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

  reset(index) {
    this.mode = 'indeterminate';
    this.processing = true;
    let self = this;
    let fileReference = this.firebaseClientService.storage().refFromURL(this.selectedFile);
    fileReference.delete().then(function () {
      self.selectedFile = null;
      self.processing = false;
      try {
        document.getElementById('image-' + index)['value'] = "";
      } catch (e) {
      }
    }).catch(function (error) {
      self.processing = false;
      console.log(error);
    });
  }

  remove(file) {
    let fileReference = this.firebaseClientService.storage().refFromURL(file);
    fileReference.delete();
  }

  uploadFile(file) {
    this.mode = 'determinate';
    this.processing = true;

    let self = this;
    let oldFile = null;

    if (this.selectedFile != null) {
      oldFile = this.selectedFile;
    }

    let storage = this.firebaseClientService.storage().ref();
    let fileRef = storage.child('images/' + new Date().getMilliseconds() + "" + file.name);

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
        if (oldFile != null) {
          self.remove(oldFile);
        }
      });
    });
  }

  onfileSelected(fileInput) {

    if (!fileInput.target.files || !fileInput.target.files[0] || !fileInput.target.files[0].type.toLowerCase().includes('image')) {
      return;
    }
    let file = fileInput.target.files[0];

    if (file.type.toLowerCase().includes('image/heic')) {
      let blob = new Blob([file]);
      this.mode = 'indeterminate';
      this.processing = true;
      heic2any({
        blob,
        toType: "image/jpeg",
        quality: 0.5
      }).then((result) => {
        this.processing = false;
        this.uploadFile(new File(
          [result as Blob],
          file.name.replace('.heic', '.jpeg'),
          )
        );
      }).catch(error => {
        this.processing = false;
        console.log(error);
      })
    } else {
      this.uploadFile(fileInput.target.files[0]);
    }
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
