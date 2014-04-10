DESCRIPTION = "An image that includes some vtk demo applications"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

IMAGE_FEATURES += "\
x11-base \
splash \
package-management \
ssh-server-dropbear \
"

inherit core-image

IMAGE_INSTALL += "\
${CORE_IMAGE_BASE_INSTALL} \
${ROOTFS_PKGMANAGE_BOOTSTRAP} \
cmake \
libxt expat freetype jpeg libxml2 libpng zlib tiff libogg libtheora \
"
